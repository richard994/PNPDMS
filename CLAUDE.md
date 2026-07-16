# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project overview

Miju_price_calc is a Java web app for fabric/textile price quoting and product development tracking. Backend: Java 14 (compiled release), Maven, deployed as a WAR to Tomcat 9. Frontend: JSP + jQuery, no build step (no npm/webpack — JS/CSS are static files served straight from `webapp`). Database: MySQL, accessed via raw JDBC (no ORM).

## Build

```bash
mvn clean package        # produces target/*.war
mvn compile               # compile only
```

This is also an Eclipse project (`.classpath`/`.project`, WTP/m2e facets) intended to be run via "Run on Server" against a local Tomcat 9 install rather than a CLI server command.

## Tests

```bash
mvn test          # runs src/test/java/Util — JUnit 5
```

Only DB-independent logic has automated coverage (`JsonUtil`, `Developments.compare`/`checkPhase`, `DataParser`'s parsing helpers, `FilterService.filter`) — see `docs/TESTING.md` for what's covered and why the DB/JSP/S3 paths aren't. Everything else is verified by hand against a real deployment using `docs/QA_CHECKLIST.md`; run it before any release and always after touching `DbUtil`, a DAO, `JsonUtil`, `WEB-INF/jspf/`, or `css/shared.css`.

## Database

- `src/main/java/Util/DbUtil.java` is the single connection provider for every DAO: it looks up a JNDI `DataSource` at `java:comp/env/jdbc/MijuPrice` (pooled, configured server-side in Tomcat — see `docs/DEPLOYMENT.md`) and falls back to `DriverManager` using `Util.Constant`'s hardcoded values if no such resource is bound (e.g. local dev with no JNDI setup). Never hand-roll a `DriverManager.getConnection(...)` call in a new DAO method — use `DbUtil.getConnection()` in a try-with-resources.
- `Constant.DBUrl`/`DBUserName`/`DBPassword` are the local-dev fallback only; production credentials belong in Tomcat's server-side `context.xml`, never in this file or in JSON sent to the client. `DBUrl` includes `useUnicode=true&characterEncoding=UTF-8` — without it, the JDBC connection negotiates its charset from the MySQL server's session defaults rather than the `utf8mb4` column definitions, which can silently mangle non-ASCII text on read even though it's stored correctly. If you add/change a server-side JNDI `Resource` (see `docs/DEPLOYMENT.md`), keep its `url` in sync with this.
- Full schema/seed SQL lives at `src/main/webapp/MijuPrice.sql`.
- Seven tables: `comment`, `development`, `log`, `materials` (yarn info attached to a saved quote), `mats` (yarn/material catalog synced from an external ERP), `quotes` (saved price calculations), `user`.
- `DevData.getFilteredDevelopments(...)` is a SQL-based equivalent of `FilterService.filter()` (dynamic `WHERE` clause instead of loading the whole table and filtering in Java). It's currently running in shadow mode only — `FilterService.SERVE_SQL_FILTER = false` means it runs alongside the old path purely to log mismatches, and the old path still serves every request. See the "SQL filter rewrite" section in `docs/QA_CHECKLIST.md` before touching either path or flipping that flag. If you change `filter()`'s criteria, you must mirror the change in `getFilteredDevelopments(...)` (and its `BINARY`/`LIKE`-escaping — string matching there is deliberately case-sensitive to match Java's `.equals()`, which the column's default `utf8mb4_unicode_ci` collation is not).

## Architecture

**Servlet-per-action, no framework, annotation-registered.** Every backend endpoint is a `HttpServlet` under `Util`, registered purely via `@WebServlet` — there is no `web.xml` (it used to exist at the wrong path, `WEB-INF/lib/web.xml`, which Tomcat never actually read; it was deleted rather than fixed since moving it to the correct location as-is would have registered duplicate servlets for every endpoint and tried to eagerly load four classes — `CopyService`/`DeleteService`/`EditService`/`FinishService` — whose `.java` source had been deleted in old refactors). `CalcService` and `SaveNewDevService` now handle what those old servlet names suggest (copy/delete/edit) via an `action` request parameter instead of separate classes. Servlets read request parameters directly (`request.getParameterValues(...)`), talk to the DB through small per-domain data-access classes (`PriceData`, `DevData`, `DataParser`, `UserData`, all via `DbUtil`), and either forward to a JSP or redirect. There is no service/repository layering beyond these data classes; SQL is inline in Java using `PreparedStatement`.

**Two parallel domains, sharing one app:**
- *Pricing*: `createqp.jsp` (build a quote) → `CalcService` (`action=copy|delete|edit|<default create>`) / `CreateService` → `PriceData`/`Quotes`/`Mats` → `success.jsp`. Saved quotes are browsed/searched/filtered/sorted on `pricing.jsp` via `PricingService`/client-side JS filtering.
- *Development tracking*: `AddNewDev.jsp` (new product dev entry) → `SaveNewDevService` (`action=create|edit|delete|duplicate|multedit`) / `NewDevService` → `DevData`/`Developments`/`Comment`/`Log` → `DevSuccess.jsp`. Saved developments are browsed on `tracker.jsp` via `TrackerService`/`FilterService`, batch-edited via `MultEdit.jsp`/`MultEdit.java`, and exported to Excel (Apache POI) via `ExportService`.

Both domains share `material.jsp`/`MatService` (yarn catalog lookup + ERP-code stats) and `auth.jsp`/`LoginService`/`LogoutService` for login.

**External sync.** `Scheduler` (a `ServletContextListener`, `@WebListener`) starts a daily background job on app startup that runs `DataParser`, which pulls yarn/material data and product-development data from an external ERP HTTP API (`http://218.75.78.226:9000/zhongya_eos/...`, 10s connect / 30s read timeout) and upserts it into the local `mats`/`development` tables. This is the only outbound network dependency.

**Request → JSP data handoff.** JSPs are not passive templates — each servlet stuffs specific `request` attributes the JSP expects by name (e.g. `CreateService` sets `mats`, `edit`, `quote`, `quoteMats`; `NewDevService` sets `user`, `create`, `edit`, `dev`, `comments`, `logs`, `fullLogs`). When modifying a servlet or its JSP, check both sides — the contract isn't typed anywhere. See `README.md` for the per-page breakdown of what each JSP expects and which JS file drives it. Any value serialized to JSON for embedding into a JSP `<script>` block **must** go through `Util.JsonUtil.toScriptSafeJson(...)`, not a raw `ObjectMapper` — it neutralizes `<` so a free-text DB field (memo/note/comment) containing `</script>` can't break the page or inject script. Embed the result directly as a JS value (`var x = ${...};`), never wrapped in JS string quotes (`'${...}'`) — a stray `'` in the data would break out of the string.

**Shared frontend layout.** `WEB-INF/jspf/navbar.jsp` is the one top navbar (despite living in a `jspf` directory, it must keep the `.jsp` extension — Tomcat's default servlet mapping only routes `*.jsp` through the JSP engine; a `.jspf` extension gets served as a raw static file instead, dumping unprocessed JSP source onto the page), included via `<jsp:include>` after setting `activePage` (and optionally `accountVariant`: `plain` for the pre-login page, `toggle` for the page reachable both logged-in and out, default is the logged-in dropdown) as a request-scope `<c:set>`. `css/shared.css` holds the `#headbar`/breadcrumb/`.dropdown-menu` rules every page used to duplicate inline. Add new pages using this pattern rather than copy-pasting the navbar markup again.

## Notable conventions

- Frontend text/labels mix Chinese and English; comments in some Java/README content are Chinese — match the existing language when editing a given file rather than translating it.
- No dependency injection, no ORM, no REST/JSON API layer for the core CRUD flows — forms POST directly to servlets and get server-side redirects/forwards back to JSP.
- `target/` and compiled `.class` files are committed to this repo's git history (see `git status` in your session) — be careful not to treat stale committed artifacts in `target/` as source of truth; the `.java` files under `src/main/java` are authoritative.
- `docs/` holds deployment (`DEPLOYMENT.md`, `context.xml.template`), local DB replication from prod (`LOCAL_DB_SETUP.md`), testing (`TESTING.md`), and QA (`QA_CHECKLIST.md`) notes — check these before changing DB wiring, uploads, or shared frontend files.
- `README.md` describes the project structure in Chinese (matches the team's convention) — keep it in sync with `webapp/` structure when adding/moving JSPs or shared frontend files; this file (`CLAUDE.md`) stays in English and focuses on what a future coding session needs to know, not a page-by-page inventory.
