# Deployment notes

## Character encoding

`Constant.DBUrl` now includes `?useUnicode=true&characterEncoding=UTF-8`.
Without it, the JDBC connection negotiates its charset from the MySQL
server's session defaults rather than from the `utf8mb4` column definitions
in `MijuPrice.sql` — if the server's default session charset isn't
`utf8mb4`, text with accents, curly quotes, or CJK characters comes back
garbled even though it's stored correctly (verifiable by querying the DB
directly with a client that negotiates its own correct session charset).
If you're still on the `DriverManager` fallback path (no JNDI `Resource`
configured yet — see below), this fix applies automatically on the next
redeploy, no server-side action needed. If you've already configured the
JNDI `Resource`, update its `url` attribute in the server-side `context.xml`
to match (see the snippet under "2. Declare the resource..." below).

## Database connection (JNDI DataSource)

The app used to open a raw `DriverManager` connection per DB call, with the
URL/username/password hardcoded in `Util.Constant` (and therefore committed to
git). It now goes through `Util.DbUtil`, which:

1. On first use, looks up a JNDI resource named `jdbc/MijuPrice`.
2. If found, uses it (a pooled `javax.sql.DataSource` configured server-side —
   no code or WAR change needed to rotate the password later).
3. If not found (e.g. a local dev box with no JNDI resource configured), falls
   back to `DriverManager.getConnection(Constant.DBUrl, ...)` exactly as
   before, so nothing breaks until the server-side config below is added.

**Nothing needs to change to keep the app working as-is.** Do the following
on the AWS box when you're ready to get the password out of git and get
connection pooling:

### 1. Add MySQL connector + Tomcat's pooling classes to Tomcat's own classpath

Tomcat's `conf/context.xml`-based DataSource pooling needs the MySQL JDBC
driver visible to Tomcat itself (not just the app). If it isn't already
there:

```bash
cp /opt/tomcat9/webapps/Miju_price_calc/WEB-INF/lib/mysql-connector-java-*.jar /opt/tomcat9/lib/
```

(Grab the jar from a built WAR, or from `~/.m2/repository/mysql/mysql-connector-java/`.)

### 2. Declare the resource in a server-side context file

Create (or edit) **`$CATALINA_BASE/conf/Catalina/localhost/<context-path>.xml`**
— e.g. if the WAR is deployed as `Miju_price_calc.war`, that's
`/opt/tomcat9/conf/Catalina/localhost/Miju_price_calc.xml`. This file lives on
the server only — it is never part of the WAR and never touches git, so the
real password stays off of GitHub.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Context>
    <Resource name="jdbc/MijuPrice"
              auth="Container"
              type="javax.sql.DataSource"
              factory="org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory"
              driverClassName="com.mysql.cj.jdbc.Driver"
              url="jdbc:mysql://localhost:3306/MijuPrice?useUnicode=true&amp;characterEncoding=UTF-8"
              username="root"
              password="REPLACE_WITH_REAL_PASSWORD"
              initialSize="2"
              maxTotal="20"
              maxIdle="10"
              minIdle="2"
              maxWaitMillis="10000"
              validationQuery="SELECT 1"
              testOnBorrow="true"/>
</Context>
```

A copy of this template (with the placeholder password) lives at
`docs/context.xml.template` in this repo for convenience — copy it to the
path above and fill in the real password there, never in the repo.

### 3. Restart Tomcat

```bash
sudo systemctl restart tomcat9
```

Watch `logs/catalina.out` for `DbUtil: using JNDI DataSource jdbc/MijuPrice`
(printed on the first DB call) to confirm it picked up the pool instead of
falling back to `DriverManager`.

### Rolling back

Delete/rename the context file and restart Tomcat — `DbUtil` will silently
fall back to the hardcoded `Constant` values again. No app redeploy needed.

## File upload limits

`SaveNewDevService` now caps uploads (fabric picture / PID picture / test
report) at 20MB per file and 60MB per request (`@MultipartConfig`). Requests
over that are rejected before any file is read into memory, instead of the
previous unbounded behavior. Adjust the constants at the top of
`SaveNewDevService.doPost` if real-world files are routinely larger.

## Removed: `src/main/ImportedClasses/` and the stray `WEB-INF/lib/web.xml`

- `ImportedClasses` held stale, pre-compiled `.class` files for servlets
  (`CopyService`, `DeleteService`, `EditService`, `FinishService`, plus a few
  data classes) whose `.java` source was deleted years ago in old refactor
  commits. Nothing in the Maven build, the Eclipse `.classpath`, or any
  JSP/JS referenced this folder — it was dead weight, safe to delete.
- `web.xml` was at `WEB-INF/lib/web.xml`, which Tomcat never reads (the
  Servlet spec requires `WEB-INF/web.xml`) — it had been silently ignored
  since it was added. It's been deleted rather than moved: every live servlet
  is already registered via `@WebServlet` annotations, and the file's
  `<servlet>` entries for `CopyService`/`DeleteService`/`EditService`/
  `FinishService` pointed at classes that no longer exist. Moving the file to
  the correct location as-is would have made Tomcat try to eagerly load those
  four missing classes on startup (`load-on-startup=1`) **and** would have
  registered duplicate servlets for every other endpoint (web.xml's
  `<servlet-name>CalcService</servlet-name>` vs. the annotation's implicit
  name `Util.CalcService`, both mapped to the same URL) — either of which
  could fail the whole deployment. Deleting it removes that landmine
  entirely with no behavior change today.
