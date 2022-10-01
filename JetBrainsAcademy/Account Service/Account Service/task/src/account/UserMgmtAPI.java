package account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserMgmtAPI {

    @PostMapping("/signup")
    ResponseEntity<UserRegResponseCM> userRegistration(@RequestBody UserRegRequestCM request) {
        try {
            ContractModelValidator.validate(request);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<UserRegResponseCM>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<UserRegResponseCM>(new UserRegResponseCM(request), HttpStatus.OK);
    }

}
