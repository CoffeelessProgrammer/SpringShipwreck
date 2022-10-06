package account.contracts;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class PasswordChangeCM implements UserCredential {

    @JsonAlias("new_password")
    @Size(min=12, message="Password length must be 12 chars minimum!")
    private String newPassword;

    @Override
    public String getPassword() {
        return this.newPassword;
    }
}
