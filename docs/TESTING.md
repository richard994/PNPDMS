# Testing

This project has no automated test suite before this change. Two things now
exist, covering different parts of the app:

## 1. JUnit tests (`src/test/java/Util`)

Run with:

```bash
mvn test
```

These cover the logic that's DB-independent and was either newly added or
directly implicated in the bug fixes made alongside this test suite:

- `JsonUtilTest` — the `</script>`-neutralizing JSON escaping used to safely
  embed server data into JSP `<script>` blocks.
- `DataParserTest` — `checkFinishingType`/`checkProductType`/
  `parseSafeDouble`, the pure parsing helpers used when syncing product data
  from the external ERP feed.
- `DevelopmentsTest` — `compare()` (the reflection-based diff that generates
  audit log entries) and `checkPhase()` (detects when a development's phase
  should advance).
- `FilterServiceTest` — `FilterService.filter()`, the tracker page's filter
  predicate. This one is the most valuable of the four: it directly exercises
  the null-safety fixes made when the servlet's old `private static` fields
  (shared, and thus corruptible, across concurrent requests) were converted
  to per-request locals passed explicitly into `filter()`.

`DataParser.checkFinishingType`/`checkProductType`/`parseSafeDouble` and
`FilterService.filter()` were relaxed from `private` to package-private
specifically so these tests can call them directly — this doesn't change any
public API, since both classes are still only reachable from within the
`Util` package or via their servlet URL mappings.

**What's deliberately not covered**: anything touching `DbUtil`/JDBC
(`PriceData`, `DevData`, `UserData`, the DB-facing half of `DataParser`),
JSP rendering, or S3 uploads (`SaveNewDevService`). Testing those for real
would mean either standing up a real MySQL instance and an embedded servlet
container as part of the build, or mocking the DB/JNDI/S3 layers deeply
enough that the tests would mostly be re-asserting the mocks. Given the
app's size, that trade-off isn't worth it yet — those paths are covered by
the manual checklist instead.

## 2. Manual QA checklist (`docs/QA_CHECKLIST.md`)

Everything that touches the database, file uploads, or actual page
rendering — i.e., most of the app — has to be verified by hand against a
real deployment. Run through `docs/QA_CHECKLIST.md` before every release,
and always after changing `DbUtil`, any DAO, `JsonUtil`, `WEB-INF/jspf/`, or
`css/shared.css` (shared by every page, so a mistake there is highest-blast-
radius).
