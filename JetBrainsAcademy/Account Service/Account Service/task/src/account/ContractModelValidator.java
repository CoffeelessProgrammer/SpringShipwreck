package account;

public class ContractModelValidator {

    public static boolean validate(UserRegRequestCM cm) {
        if(cm.getName() == null || cm.getName().isBlank())
            throw new IllegalArgumentException("name must not be blank");
        if(cm.getLastname() == null || cm.getLastname().isBlank())
            throw new IllegalArgumentException("lastname must not be blank");
        if(cm.getEmail() == null || !cm.getEmail().endsWith("@acme.com"))
            throw new IllegalArgumentException("invalid email");
        if(cm.getPassword() == null || cm.getPassword().isBlank())
            throw new IllegalArgumentException("password must not be blank");

        return true;
    }
}
