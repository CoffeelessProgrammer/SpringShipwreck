# Spring Security Experiments

## Resources
- [Spring Security Docs](https://spring.io/projects/spring-security)
- [Spring Security without WebSecurityConfigurerAdapter (v5.7+)](https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter)
- [Limit Login Attempts | CodeJava](https://www.codejava.net/frameworks/spring-boot/spring-security-limit-login-attempts-example)
- [Preventing Brute Force Authentication Attempts | Baeldung](https://www.baeldung.com/spring-security-block-brute-force-authentication-attempts)

## Problems Encountered
- **Issue:** Unable to get instance of AuthenticationManager for a custom UsernamePasswordAuthenticationFilter
    - [Retrieving AuthenticationManager w/o WebSecurityConfigurerAdapter ](https://stackoverflow.com/questions/73378676/spring-boot-custom-authentication-filter-without-using-the-websecurityconfigur)
    - **Solution:** API Change – `AuthenticationManagerBuilder#getOrBuild`