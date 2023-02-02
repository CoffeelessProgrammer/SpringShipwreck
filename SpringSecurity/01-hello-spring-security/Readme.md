# Hello Spring Security

## Resources
- [Spring Security for Java Backend Devs | JetBrains Academy](https://hyperskill.org/tracks/38?category=2)
- [Upgrading the Deprecated WebSecurityConfigurerAdapter | Baeldung](https://www.baeldung.com/spring-deprecated-websecurityconfigureradapter)
- [Tidbit: anonymous() vs permitAll()](https://stackoverflow.com/questions/51395906/understanding-the-difference-of-permitall-and-anonymous-in-spring-security)

## Problems Encountered
- **Issue:** User remains authenticated after logout, i.e. can still access role-gated endpoints
    - [Spring Security logout not working](https://stackoverflow.com/questions/36557294/spring-security-logout-does-not-work-does-not-clear-security-context-and-authe)
    - **Solution:** Set `clearAuthentication(true)` in `HttpSecurity#logout`
