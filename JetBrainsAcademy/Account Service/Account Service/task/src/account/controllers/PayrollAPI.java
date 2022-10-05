package account.controllers;

import account.contracts.UserRegResponseCM;
import account.models.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PayrollAPI {

    @GetMapping("/empl/payment")
    ResponseEntity<UserRegResponseCM> getEmployeePayroll(@AuthenticationPrincipal UserEntity principal) {
        return new ResponseEntity<UserRegResponseCM>(new UserRegResponseCM(principal), HttpStatus.OK);
    }
}
