package account.contracts;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class PayrollCM {
    @NotBlank
    @Email(regexp=".*@acme\\.com", flags={Pattern.Flag.CASE_INSENSITIVE})
    @JsonAlias("employee")
    private String employeeEmail;
    @NotBlank
    @Pattern(regexp = "[0-1]\\d-20\\d{2}")
    private String period;
    @NotNull
    @Min(0)
    private long salary;

    public void emailToLowercase() {
        this.employeeEmail = employeeEmail.toLowerCase();
    }

    public void setEmployeeEmail(String email) {
        this.employeeEmail = email.toLowerCase();
    }
}
