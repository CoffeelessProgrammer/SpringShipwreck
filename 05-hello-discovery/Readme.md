# Service Discovery POC

- `java -jar EurekaDiscovery/build/libs/EurekaDiscovery-0.0.1-SNAPSHOT.jar`
- `java -jar PokemonSettlements/build/libs/PokemonSettlements-0.0.1-SNAPSHOT.jar`
- `java -jar ApiGateway/build/libs/ApiGateway-0.0.1-SNAPSHOT.jar`

## Resources
- [Spring Cloud Netflix | Spring Docs](https://spring.io/projects/spring-cloud-netflix)
- [Intro to Netflix Eureka | Baeldung](https://www.baeldung.com/spring-cloud-netflix-eureka)
- [Spring Cloud Eureka Server | Spring Cloud Docs](https://cloud.spring.io/spring-cloud-netflix/reference/html/#spring-cloud-eureka-server)

## Problems Encountered
- **Issue:** `spring.cloud.gateway.discovery.locater.enabled=true`, but Gateway cannot find existing routes through Eureka
    - **Solution:** Spelled `locator` wrong... keep yer eyes peeled 👀