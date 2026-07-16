# Replicating the production database locally

The most reliable way to test any DB-touching change (like the filtering
rewrite below) is against a real copy of production data, not synthetic
local data. This is a one-time (or occasional-refresh) setup, and read-only
from production's perspective — `mysqldump` doesn't lock or modify anything
meaningfully (with `--single-transaction`, InnoDB tables are dumped from a
consistent snapshot without blocking other queries).

## Steps

**1. On the AWS server**, dump the database:

```bash
mysqldump -u root -p --single-transaction --routines --triggers \
  MijuPrice > mijuprice_snapshot.sql
```

`--single-transaction` avoids table locks (safe to run while the app is
live); `--routines --triggers` picks up any stored procedures/triggers if
there are any (harmless to include if there aren't).

**2. Copy the dump to your local machine:**

```bash
scp your-user@your-aws-host:~/mijuprice_snapshot.sql .
```

(Adjust for however you normally SSH in — key file, bastion host, etc.)

**3. On your local machine**, create a fresh database and import:

```bash
mysql -u root -p -e "CREATE DATABASE MijuPrice CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
mysql -u root -p MijuPrice < mijuprice_snapshot.sql
```

**4. Point your local `Constant.DBUrl`** (or a local JNDI `Resource` if
you've set one up) at this database — it already defaults to
`jdbc:mysql://localhost:3306/MijuPrice`, so if your local MySQL matches that
host/port/db name, no code change is needed.

## Before you do this

- The `user` table has plaintext-style passwords (see `Constant.java`'s own
  hardcoded example) — treat the dump file as sensitive. Don't commit it,
  and delete it once you're done (`rm mijuprice_snapshot.sql`).
- If you want to scrub real user data instead of just being careful with the
  file, you can `UPDATE user SET password = 'test123';` locally after
  import — the dump only needs to be realistic in the `development`,
  `comment`, `log`, `mats`, and `quotes` tables to be useful for testing the
  filter rewrite.

## Refreshing later

Same three steps — a fresh dump/copy/import overwrites the local DB with a
newer snapshot whenever you want more current data to test against.
