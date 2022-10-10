package account.contracts;

import account.models.PayrollEntity;
import account.models.UserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonPropertyOrder({"name", "lastname", "period", "salary"})
public class PayrollResponseCM {
    @JsonProperty("name")
    private String firstName;
    @JsonProperty("lastname")
    private String lastName;
    private String period;
    private String salary;

    public PayrollResponseCM(PayrollEntity payrollEntity, UserEntity employee) {
        LocalDate period = payrollEntity.getPayrollId().getPeriod();
        String month = period.getMonth().name();
        this.period = month.charAt(0) + month.substring(1).toLowerCase() + "-" + period.getYear();
        this.salary = salaryToString(payrollEntity.getSalary());
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
    }

    private static String salaryToString(long salary) {
        return salary/100 + " dollar(s) " + salary%100 + " cent(s)";
    }
}
