package account.contracts;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserRegistrationCM {

    @NotBlank
    private String name;
    @NotBlank
    private String lastname;
    @NotNull
    private String email;
    @NotBlank
    private String password;

}
