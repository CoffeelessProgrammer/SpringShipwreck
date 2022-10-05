package account.controllers;

import account.services.UserEntityManager;
import account.contracts.UserRegistrationCM;
import account.contracts.UserRegResponseCM;
import account.models.UserEntity;
import account.validation.ContractModelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class UserMgmtAPI {

    @Autowired
    UserEntityManager usersService;

    @PostMapping("/signup")
    ResponseEntity<UserRegResponseCM> userRegistration(@RequestBody UserRegistrationCM request) {
        UserEntity newUser;

        try {
            ContractModelValidator.validate(request);
            newUser = usersService.createUser(new UserEntity(request));
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<UserRegResponseCM>(new UserRegResponseCM(newUser), HttpStatus.OK);
    }

}
