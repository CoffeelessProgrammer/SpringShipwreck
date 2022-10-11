package account.validation;

import account.contracts.PayrollCM;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;

public class ContractValidator {

    private static final Pattern unsafePasswordPattern = Pattern.compile(".*password.*", Pattern.CASE_INSENSITIVE);

    public static boolean validatePassword(UserCredential credential) {
        if(unsafePasswordPattern.matcher(credential.getPassword()).matches())
            throw new BreachedPasswordException();

        return true;
    }

    public static boolean validate(PayrollCM payrollCM) {
        validatePayrollPeriod(payrollCM.getPeriod());

        return true;
    }

    public static boolean validatePayrollPeriod(String period) {
        if(period==null) return false;
        if(period.length() == 0) return true;

        final int month = Integer.parseInt(period.split("-")[0]);
        if(month > 12 || month < 1)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid month");

        return true;
    }
}
