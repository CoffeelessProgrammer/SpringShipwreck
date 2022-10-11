package account.controllers;

import account.contracts.*;
import account.models.UserEntity;
import account.services.UserEntityManager;
import account.validation.ContractValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class UserMgmtAPI {

    @Autowired
    UserEntityManager usersService;

    @PostMapping("/signup")
    ResponseEntity<UserInfoCM> userRegistration(@RequestBody @Valid UserRegistrationCM request, Errors errors) {
        if(errors.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldError().getDefaultMessage());
        }
        ContractValidator.validatePassword(request);
        UserEntity newUser = usersService.createUser(new UserEntity(request));
        return new ResponseEntity<>(new UserInfoCM(newUser), HttpStatus.OK);
    }

    @PostMapping("/changepass")
    ResponseEntity<PasswordChangeResponseCM> changePassword(@AuthenticationPrincipal UserEntity user,
                                                            @RequestBody @Valid PasswordChangeCM request,
                                                            Errors errors) {
        if(errors.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldError().getDefaultMessage());
        }
        PasswordChangeResponseCM response = new PasswordChangeResponseCM(user.getUsername());
        ContractValidator.validatePassword(request);
        usersService.changePassword(user.getPassword(), request.getNewPassword());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}