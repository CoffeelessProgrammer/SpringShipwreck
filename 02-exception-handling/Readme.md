# Exception Handling

## Resources
- [@Valid and @Validated | Medium](https://medium.com/javarevisited/are-you-using-valid-and-validated-annotations-wrong-b4a35ac1bca4)
    - `MethodArgumentNotValidException`
    - `ConstraintViolationException`
- [Error Handling for REST | Baeldung](https://www.baeldung.com/exception-handling-for-rest-with-spring)

## Problems Encountered
- **Issue:** Customized validation error messages not showing in response body
    - "Validation failed for object='userRegistrationCM'. Error count: 1" instead of "Password must be at least 12 characters"
    - ["Validation failed for object='user'. Error count: 1"](https://stackoverflow.com/questions/65023750/validation-failed-for-object-user-error-count-1-instead-of-email-must-not)
    - **Solution:** Parse `Errors` object to retrieve custom message for each error