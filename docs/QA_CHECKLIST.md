# Manual QA checklist

There's no automated UI/integration test suite for this app (see
`docs/TESTING.md` for what *is* automated and why). Run this checklist
against a real deployment (local Tomcat + MySQL, or staging) before every
release, and especially after touching `Util.DbUtil`, any DAO
(`PriceData`/`DevData`/`DataParser`/`UserData`), `JsonUtil`, or anything under
`WEB-INF/jspf/`, `css/shared.css` — those are shared by every page.

Use two browser profiles (or one normal + one incognito) so you can check
concurrent-user behavior where noted.

## SQL filter rewrite — shadow verification & cutover

`FilterService` now has two ways to compute the tracker's filtered
development list: the original in-memory Java filter (`filter()`, still
what's actually served) and a new SQL-based one
(`DevData.getFilteredDevelopments`). While `FilterService.SERVE_SQL_FILTER`
is `false` (the default), **nothing about what users see changes** — the
SQL path runs alongside the old one on every filter request purely for
comparison, and logs one line per request:

```
FILTER SHADOW CHECK: match (42 developments)
```
or, if the two disagree:
```
FILTER MISMATCH: java-only dev_ids=[17] sql-only dev_ids=[]
```

Before flipping the switch:

- [ ] Deploy with `SERVE_SQL_FILTER = false` (the default) and let it run
      against real production traffic for a while (a day of normal tracker
      usage is a reasonable bar, more if the team's filter usage is light).
      Watch `catalina.out`/the console log for any `FILTER MISMATCH` lines.
- [ ] If you set up a local replica of prod data (see
      `docs/LOCAL_DB_SETUP.md`), exercise every filter combination in
      section 6 below against it, watching the same log lines — this is
      faster than waiting on real traffic and lets you test edge cases
      (empty results, boundary price ranges, codes containing `%`/`_`,
      mixed-case codes/statuses) deliberately rather than hoping real
      usage happens to hit them.
- [ ] If a mismatch ever appears, do not flip the switch — the dev_ids in
      the log tell you which records to inspect (`getDevelopmentById` via
      the tracker, or a direct query) to figure out which criterion the SQL
      version is handling differently, and whether it's the SQL side or the
      original Java side that's "right" (the SQL version fixes nothing on
      purpose — any behavior difference is a bug in the SQL translation to
      fix, not an intentional change).
- [ ] Once confident: flip `SERVE_SQL_FILTER` to `true`, redeploy, and keep
      watching for a while — the shadow comparison keeps running and
      logging even after cutover (it's cheap to leave in), so a regression
      would still show up as a `FILTER MISMATCH` even though the SQL side
      is now the one being served. Once you're comfortable, the old
      Java-side `filter()`/`removeIf()` path and the shadow-check call can
      be deleted.

## 0. Setup sanity

- [ ] `mvn clean package` succeeds and produces a `.war`.
- [ ] App deploys to Tomcat without errors in `catalina.out` (watch for
      `DbUtil: using JNDI DataSource` vs the DriverManager fallback message —
      confirms which DB path is active).
- [ ] Visiting `/` with no session redirects/shows the logged-out home page,
      not an error.

## 1. Login / logout

- [ ] Wrong email or password on `/auth.jsp` shows the "incorrect
      username/password" alert, doesn't crash.
- [ ] Submitting the login form with the `email` or `password` field
      empty (or via a raw request with the param missing) doesn't 500 —
      should bounce back to the login page.
- [ ] Correct credentials log in and land on the home page with the
      "Account" nav item now showing a **Log Out** dropdown instead of the
      plain "Account" link.
- [ ] Log Out returns to the logged-out home page and revisiting a
      logged-in-only URL (e.g. `/TrackerService`) redirects to `/auth.jsp`.
- [ ] Session persists across the four main tabs (Home/Pricing/Material/
      Tracker) without re-prompting login.

## 2. Navbar / shared layout (regression check for the include refactor)

- [ ] On every page (Home, Auth, Create Quote, Success, Pricing, Material,
      Tracker, Add/Edit Dev, Dev Success, Multi-Edit), the correct nav item
      is highlighted blue for that page.
- [ ] The breadcrumb icons (home icon, arrow separators) render correctly
      on pages that show a breadcrumb — this exercises `css/shared.css`'s
      image paths (`../home.png`, `../discount.png` relative to `css/`).
- [ ] Browser console has no 404s for `css/shared.css` or any image
      (previously `material.jsp` 404'd on a nonexistent `js/shared.css`).

## 3. Price calculator (`createqp.jsp` / `CalcService` / `CreateService`)

- [ ] Create a new quote with several materials, submit, land on the
      success page with the correct calculated price.
- [ ] Edit an existing quote (via Pricing page's EDIT button) — form
      pre-fills with the original values, re-submitting updates it (old
      quote row is gone, new one has the edits).
- [ ] Copy a quote from the Pricing page — a duplicate appears with the
      same values and a new ID.
- [ ] Delete a quote — it disappears from the Pricing table and its
      materials are gone too (no orphaned rows).
- [ ] **Concurrency check**: open the calculator in two tabs as if two
      people are creating quotes at once; submit tab A, then tab B, with
      different model names. Confirm both quotes saved with their own
      correct data (regression check for the removed `static` fields in
      `CalcService`).

## 4. Pricing dashboard (`pricing.jsp`)

- [ ] Table loads, sortable/paginated via DataTables.
- [ ] Text search (product model) filters rows.
- [ ] Date filter matches only that date's rows.
- [ ] **Finishing-module multi-select filter**: select two or more
      finishing codes; rows whose finishing list contains *any* of the
      selected codes should show, others hide (this filter logic was
      fixed — verify it actually narrows results instead of showing
      everything or nothing).
- [ ] Delete confirmation modal: open it for row A, cancel, open it for row
      B, confirm — row B (not A) should be deleted (regression check for
      the missing `.off()` before `.on('click', ...)`).

## 5. Material dashboard (`material.jsp`)

- [ ] Yarn/material list loads with correct per-category counts.
- [ ] Search narrows the list.

## 6. Development tracker (`tracker.jsp` / `TrackerService` / `FilterService`)

- [ ] First visit ever in a fresh session (no prior filter submitted) does
      not throw a JS error in the console (regression check for the old
      `JSON.parse('${filterdev}')` on an empty/unset session attribute).
- [ ] Apply a filter with a title/code search that includes an apostrophe
      (e.g. search text `O'Brien`) — should not break the page or throw a
      JS syntax error (regression check for the JSON-in-`<script>`
      escaping fix).
- [ ] Apply several filter criteria (checkboxes, price range slider,
      season/design/etc. dropdowns, strike-off/blanket/test multi-selects) —
      results match all selected criteria.
- [ ] Clear/omit a filter criterion that was previously set, resubmit —
      confirm it no longer restricts results (regression check for the
      old "stale static filter" bug: previously an omitted checkbox/field
      could keep filtering by whatever the *last* request set).
- [ ] Paginate through filtered results; jump to a page number beyond the
      last page (edit the URL's `page` param manually) — should clamp to
      the last page, not 500.
- [ ] Sort toggle reverses order correctly.
- [ ] Duplicate a development from a card — confirm modal targets *that*
      card's `devId`, not a previously-opened one (regression check for
      the duplicated `#DupModal`/`#confirmDuplicate` ids and missing
      `.off()`). Try duplicating two different cards in the same session
      without a page reload in between.
- [ ] Export to Excel (filtered and unfiltered) — downloaded file opens
      and matches the on-screen list.
- [ ] No filter criteria at all (fresh tracker page, nothing checked)
      shows only active developments, not inactive ones — this is the
      existing "default to active" behavior and must keep working
      identically after the SQL filter rewrite.
- [ ] Check both "Active" and "Inactive" boxes together — shows both
      (the `showAll` case); check only "Inactive" — shows only inactive.
- [ ] Search a code/color containing a literal `%` or `_` if any exist in
      your data (or temporarily rename one to test) — should match only
      that literal text, not act as a SQL wildcard.
- [ ] If any codes/statuses in your data have inconsistent casing, confirm
      filtering by season/design type/colorist/etc. and the multi-select
      statuses stays case-sensitive (an exact-case match), matching the
      pre-rewrite behavior — see the shadow-verification section above for
      how to catch a case-sensitivity mismatch before it reaches users.
- [ ] Boundary price range: set min/max equal to a development's exact
      cost — it should be included (the range is inclusive on both ends).

## 7. Add / edit development (`AddNewDev.jsp` / `NewDevService` /
   `SaveNewDevService`)

- [ ] Create a new development with all fields filled, submit — lands on
      Dev Success, appears correctly in the tracker.
- [ ] Edit an existing development, change a few fields, save — old
      record is replaced, a log entry records what changed (`Updated X
      from Y to Z`).
- [ ] Edit a development and change *nothing* — no new log entry, no
      duplicate record left behind.
- [ ] Add a comment (Leah/US/Mill/George), verify it displays and is
      preserved across further edits (old comments are copied to the new
      record).
- [ ] Delete a comment via its own trash icon — only that comment goes,
      others remain (regression check for the `.off()`/`.on()` fix on
      `#confirmCmtDelete`).
- [ ] Upload a fabric picture / PID / test report image on create, then
      again on edit — old file handling doesn't 404 or duplicate
      unexpectedly.
- [ ] Try uploading a file larger than 20MB — should be rejected
      gracefully (bounced back to the tracker, no 500) instead of hanging
      or crashing the request. *(New: `@MultipartConfig` limits.)*
- [ ] Delete a development — its uploaded files are removed from S3 too
      (or at least the delete doesn't error).
- [ ] Duplicate a development from the Add/Edit page (if applicable) or
      Tracker — files are copied under a new S3 key, not shared with the
      original.
- [ ] View-only mode (if reachable) doesn't allow edits.
- [ ] Navigate to `NewDevService?action=edit` with a missing or
      non-numeric `devId` — should redirect to the tracker, not 500.

## 8. Multi-edit (`MultEdit.jsp`)

- [ ] Add a development by code, confirm it renders an editable block.
- [ ] Try adding the *same* development twice — should show "already
      added" and not create a duplicate block. *(New guard.)*
- [ ] Edit a couple of fields across two different added developments,
      save all — both updates persist, and log entries are created only
      for the fields that actually changed.
- [ ] Remove a block before saving — it's excluded from the submit.

## 9. Background ERP sync (`Scheduler` / `DataParser`)

This runs once daily and isn't practical to test end-to-end in a QA pass,
but sanity-check after any change to `DataParser`:

- [ ] App starts even if the external ERP endpoint
      (`218.75.78.226:9000`) is unreachable — should log the exception and
      continue serving the rest of the app (it must not block startup).
- [ ] If reachable, confirm `mats` and `development` tables get updated
      (check `whiteprice`/`content`/`finishing_used` etc. change) after a
      manual trigger or the next scheduled run.

## 10. Resilience checks (only if you can safely simulate)

- [ ] Stop MySQL momentarily and hit any page that reads/writes the DB —
      should show a real `SQLException`-driven error, not a
      `NullPointerException` masking it (regression check for the
      `getConn()`/`conn.close()` NPE fix — now surfaced via `DbUtil`).
- [ ] With the JNDI `Resource` configured (see `docs/DEPLOYMENT.md`),
      confirm the app logs `DbUtil: using JNDI DataSource` and DB calls
      still work; then remove the resource and restart — app should fall
      back to `DriverManager` silently and keep working.
