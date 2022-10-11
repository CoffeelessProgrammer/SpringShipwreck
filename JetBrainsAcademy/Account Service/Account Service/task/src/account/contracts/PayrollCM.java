package account.contracts;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class PayrollCM {
    @NotNull
    @Email(regexp=".*@acme\\.com", flags={Pattern.Flag.CASE_INSENSITIVE})
    @JsonAlias("employee")
    private String employeeEmail;
    @NotNull
    @Pattern(regexp = "[0-1]\\d-20\\d{2}")
    private String period;
    @NotNull
    private long salary;

    public void emailToLowercase() {
        this.employeeEmail = employeeEmail.toLowerCase();
    }

    public void setEmployeeEmail(String email) {
        this.employeeEmail = email.toLowerCase();
    }
}
