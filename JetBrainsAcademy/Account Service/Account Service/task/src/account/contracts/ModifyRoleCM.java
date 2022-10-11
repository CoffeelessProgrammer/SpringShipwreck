package account.contracts;

import account.security.AuthorityOperation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class ModifyRoleCM {
    @JsonProperty("user")
    @NotBlank
    @Email(regexp=".*@acme\\.com", flags={Pattern.Flag.CASE_INSENSITIVE})
    private String email;

    @NotBlank
    private String role;

    @NotNull
    private AuthorityOperation operation;

    public void setRole(String role) {
        this.role = role.toUpperCase();
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }
}
