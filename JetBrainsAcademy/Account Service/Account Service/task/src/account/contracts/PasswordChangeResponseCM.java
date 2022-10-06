package account.contracts;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PasswordChangeResponseCM {

    private static final String success = "The password has been updated successfully";

    private String email;
    private String status;

    public PasswordChangeResponseCM(String email) {
        this.email = email;
        this.status = success;
    }
}
