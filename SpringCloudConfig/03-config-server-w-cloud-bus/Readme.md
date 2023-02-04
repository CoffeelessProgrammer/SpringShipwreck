# Config Server and Cloud Bus w/ RabbitMQ POC

Setting up a Spring Config Server and Spring Cloud Bus w/ RabbitMQ for modifying configuration properties from remote repo with no service downtime, i.e. no need to restart services for remote property changes.

Request POST on Config Server's `/actuator/busrefresh` endpoint to propagate remote property changes to subscribed client services.

## Resources
- [Spring Cloud Bus | Spring Docs](https://docs.spring.io/spring-cloud-bus/docs/current/reference/html/)
- [RabbitMQ Installation | RabbitMQ](https://rabbitmq.com/download.html)
- [Spring Cloud Config | Spring Docs](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/)

## Problems Encountered
- **Issue:** `405 Method Not Allowed` response when accessing `/actuator/busrefresh` endpoint on Config Server
    - **Solution:** Add the `spring-boot-starter-actuator` dependency to classpath