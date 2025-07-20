# Project XX: TEMPLATE

- [mongo | Docker Hub](https://hub.docker.com/_/mongo#initializing-a-fresh-instance)

## Env Setup Notes



## docker-compose.yaml

```yaml
services:
  db-mongo:
    image: mongo
    environment:
      MONGO_INITDB_ROOT_USERNAME: admin
      MONGO_INITDB_ROOT_PASSWORD: pass
    volumes:
    - ./data:docker-entrypoint-initdb.d
  ui-mongo-express:
    image: mongo-express
    depends_on:
    - db-mongo
    restarts: always
    ports:
    - "8081:8081"
    environment:
    - ME_CONFIG_MONGODB_SERVER=db-mongo
    - ME_CONFIG_MONGODB_ADMINUSERNAME=admin
    - ME_CONFIG_MONGODB_ADMINPASSWORD=pass
```

```properties
# COMMENTED_PROP=1
```

_____

## Challenges Encountered
1. **Context:** <br>**Error:** <br>**Obs:** 
    - **Solution:** 
    - **!REF**
1. **Context:** <br>**Issue:** <br>**Obs:** 
    - **Solution:** 
    - **!REF**