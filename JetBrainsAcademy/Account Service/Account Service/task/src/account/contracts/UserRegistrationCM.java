package account.contracts;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserRegistrationCM implements UserCredential {

    @NotBlank
    private String name;
    @NotBlank
    private String lastname;
    @NotBlank
    @Email(regexp=".*@acme\\.com", flags={Pattern.Flag.CASE_INSENSITIVE})
    private String email;
    @NotBlank
    @Size(min=12, message="The password length must be at least 12 chars!")
    private String password;

}
