package account.controllers;

import account.contracts.PayrollCM;
import account.models.UserEntity;
import account.services.PayrollService;
import account.validation.ContractValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/api")
public class PayrollAPI {

    @Autowired
    PayrollService payrollService;

    @GetMapping("/empl/payment")
    ResponseEntity<Object> getEmployeePayroll(@RequestParam Optional<String> period,
                                              @AuthenticationPrincipal UserEntity employee) {
        ContractValidator.validatePeriod(period.orElse(""));

        Object response;

        if(period.isPresent())
            response = this.payrollService.getPayrollEntry(period.get(), employee);
        else
            response = this.payrollService.getPayrollEntries(employee);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/acct/payments")
    ResponseEntity<Map<String, Object>> getEmployeePayroll(@RequestBody @Valid List<PayrollCM> payrolls) {
        for(PayrollCM payrollCM : payrolls) ContractValidator.validate(payrollCM);

        this.payrollService.addPayrollEntries(payrolls);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "Added successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/acct/payments")
    ResponseEntity<Map<String, String>> updateEmployeeSalary(@RequestBody @Valid PayrollCM payroll) {
        ContractValidator.validate(payroll);

        this.payrollService.updatePayrollEntry(payroll);

        Map<String, String> response = new HashMap<>();
        response.put("status", "Updated successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    void handleContraintViolationException(ConstraintViolationException exception,
                                           ServletWebRequest webRequest) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
    }
}
