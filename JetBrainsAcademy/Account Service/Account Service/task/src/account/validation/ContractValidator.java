package account.validation;

import account.contracts.UserCredential;

import java.util.regex.Pattern;

public class ContractValidator {

    private static final Pattern pattern = Pattern.compile(".*password.*", Pattern.CASE_INSENSITIVE);

    public static boolean validatePassword(UserCredential credential) {
        if(pattern.matcher(credential.getPassword()).matches())
            throw new BreachedPasswordException();

        return true;
    }
}
