package account.validation;

import account.contracts.PayrollCM;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;

public class ContractValidator {

    private static final Pattern pattern = Pattern.compile(".*password.*", Pattern.CASE_INSENSITIVE);

    public static boolean validatePassword(UserCredential credential) {
        if(pattern.matcher(credential.getPassword()).matches())
            throw new BreachedPasswordException();

        return true;
    }

    public static boolean validate(PayrollCM payrollCM) {
        validatePeriod(payrollCM.getPeriod());
        if(payrollCM.getSalary() < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Negative salary");

        return true;
    }

    public static boolean validatePeriod(String period) {
        if(period != null && period.length() == 0) return true;

        final int month = Integer.parseInt(period.split("-")[0]);
        if(month > 12 || month < 1)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid month");

        return true;
    }
}
