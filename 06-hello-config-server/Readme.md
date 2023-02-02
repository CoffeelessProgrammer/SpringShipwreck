# Config Server POC

Setting up a Spring Config Server with private Git repository access.

## Resources
- [Spring Cloud Config | Spring Docs](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/)
- [Spring Cloud Config | Baeldung](https://www.baeldung.com/spring-cloud-configuration)
- [Config Example | Spring Cloud Samples](https://github.com/spring-cloud-samples/config-repo)

## Problems Encountered
- **Issue:** Remote config properties not being retrieved due to failed authorization
    - **Solution:** Use a personal access token for `spring.cloud.config.server.git.password` property. Ensure that the token has proper permissions, i.e. repo read-only access.
    - "When you have two-factor authentication enabled, Basic Authentication for most endpoints in the REST API requires that you use a personal access token." - [GitHub Docs](https://docs.github.com/en/rest/overview/other-authentication-methods?apiVersion=2022-11-28#basic-authentication)