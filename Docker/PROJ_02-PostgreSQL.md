# Project 02: Create PostgreSQL Database via Docker Compose

- [postgres | Docker Hub](https://hub.docker.com/_/postgres#initialization-scripts)
- [adminer | Docker Hub](https://hub.docker.com/_/adminer)

## Setup Notes
- Env Variables
  - postgres
    - POSTGRES_DB
    - POSTGRES_USER
    - POSTGRES_PASSWORD

### PostgreSQL Data Init Script
- [PostgreSQL Docs](https://www.postgresql.org/docs/current/app-initdb.html)

```sql
-- ./data/init-postgres-data.sql

CREATE TABLE product(
  id SERIAL PRIMARY KEY,
  name VARCHAR(50),
  price NUMERIC(10,2) NOT NULL
);

INSERT INTO product(name, price)
VALUES
  ('Galaxy Note', 920.75),
  ('Galaxy Tab', 649.50);
```

## docker-compose.yaml
- `/var/lib/postgresql/data` - Def. data storage location (for volume mapping)
- `/docker-entrypoint-initdb.d` - Scripts in location will be run on db initialization

```yaml
services:
  db-postgres:
    image: postgres
    volumes:
    - ./data:/docker-entrypoint-initdb.d
    environment:
    - POSTGRES_DB=product_db
    - POSTGRES_USER=admin
    - POSTGRES_PASSWORD=pass
  adminer:
    image: adminer
    ports:
    - "8080:8080"
```

### Env Properties

```properties
# --- PostgreSQL -------------
POSTGRES_DB=product_db
POSTGRES_USER=admin
POSTGRES_PASSWORD=pass
```
_____