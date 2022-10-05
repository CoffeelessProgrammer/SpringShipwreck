package account.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // Should be Conflict
public class UsernameExistsException extends RuntimeException {

    public UsernameExistsException() {
        super("User exist!");
    }
}
