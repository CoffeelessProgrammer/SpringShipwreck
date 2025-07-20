# Project 1: Create Mongo Database via Docker Compose

- [mongo | Docker Hub](https://hub.docker.com/_/mongo#initializing-a-fresh-instance)
- [mongo-express | Docker Hub](https://hub.docker.com/_/mongo-express)

## Setup Notes
- Req. Env Variables
  - mongo
    - MONGO_INITDB_ROOT_USERNAME
    - MONGO_INITDB_ROOT_PASSWORD
  - mongo-express
    - ME_CONFIG_MONGODB_ADMINUSERNAME
    - ME_CONFIG_MONGODB_ADMINPASSWORD
    - ME_CONFIG_MONGODB_SERVER

### MongoDB Data Init Script
- [db.getSiblingDB(<db>) | MongoDB Docs](https://www.mongodb.com/docs/manual/reference/method/db.getSiblingDB/)
- [db.createCollection(<name>) | MongoDB Docs](https://www.mongodb.com/docs/manual/reference/method/db.createCollection/)
- [db.collection.insertMany() | MongoDB Docs](https://www.mongodb.com/docs/manual/reference/method/db.collection.insertMany/)

```js
// ./data/init-mongo-data.js
db = db.getSiblingDB('product-service');
db.createCollection('products');
db.products.insertMany(
  [
    {
      "title": "Galaxy Note",
      "price": 925
    },
    {
      "title": "Galaxy Tab",
      "price": 650
    }
  ]
);
```

## docker-compose.yaml
- `/docker-entrypoint-initdb.d` - Scripts in location will be run on db initialization

```yaml
services:
  db-mongo:
    image: mongo
    environment:
      MONGO_INITDB_ROOT_USERNAME: admin
      MONGO_INITDB_ROOT_PASSWORD: pass
    volumes:
    - ./data:/docker-entrypoint-initdb.d
  ui-mongo-express:
    image: mongo-express
    depends_on:
    - db-mongo
    restart: always
    ports:
    - "8081:8081"
    environment:
    - ME_CONFIG_MONGODB_SERVER=db-mongo
    - ME_CONFIG_MONGODB_ADMINUSERNAME=admin
    - ME_CONFIG_MONGODB_ADMINPASSWORD=pass
```

```properties
# --- MongoDB -------------
MONGO_INITDB_ROOT_USERNAME=admin
MONGO_INITDB_ROOT_PASSWORD=pass
# --- Mongo-Express -------
ME_CONFIG_MONGODB_SERVER=db-mongo
ME_CONFIG_MONGODB_ADMINUSERNAME=admin
ME_CONFIG_MONGODB_ADMINPASSWORD=pass
```

_____

## Challenges Encountered
1. **Context:** Running MongoDB w/ Mongo Express<br>**Error:** ui-mongo-express exited (0) <br>**Obs:** The Mongo Express container terminates before MongoDB can load up.
    - **Solution:** Add configuration to compose.yaml under dependent application, `restart: always`. Restarts container until successful connection.
1. **Context:** Running MongoDB w/ Mongo Express<br>**Issue:** No persistence of mongo data outside of container<br>**Obs:** DB data is wiped on `docker compose down`
    - **Solutions:**
      - Volume map `/data/db/` from the container to a local/project directory
        - `-v ./data:/data/db`
    - **!REF** https://hub.docker.com/_/mongo#where-to-store-data