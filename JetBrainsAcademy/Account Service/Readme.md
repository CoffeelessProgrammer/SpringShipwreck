# Account Service

## Resources
- Composite Primary Keys in JPA
    - [Composite Primary Keys | Baeldung](https://www.baeldung.com/jpa-composite-primary-keys)
    - [The Ultimate Guide on Composite IDs](https://www.jpa-buddy.com/blog/the-ultimate-guide-on-composite-ids-in-jpa-entities/)
    - [@MapsId Annotation](https://stackoverflow.com/questions/9923643/can-someone-please-explain-me-mapsid-in-hibernate)
- `lombok.noArgsConstructor.extraPrivate = true`
- **Issue:** JPS incremental annotation processing is disabled. Compilation results on partial recompilation may be inaccurate. ...
    - Suggested Fix: VM flag to fix Lombok? - `-Djps.track.ap.dependencies=true` [Doesn't work]
    - **Fix:** Manually upgraded Lombok version `1.18.12` from Spring Boot v2.3.1 to latest version of `1.18.24`