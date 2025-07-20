# Project 3: Dockerizing Spring App

- [Spring Initializer](https://start.spring.io/)
- [MongoDB Query Methods | Spring Docs](https://docs.spring.io/spring-data/mongodb/reference/mongodb/repositories/query-methods.html)

## Env Setup Notes



## docker-compose.yaml

```yaml
services:
  db-mongo:
    image: mongo
    environment:
    volumes:
    - ./data:docker-entrypoint-initdb.d
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