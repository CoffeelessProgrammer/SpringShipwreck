package account.models;

import account.contracts.PayrollCM;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Payroll")
public class PayrollEntity {
    @EmbeddedId
    PayrollPK payrollId;

    @ManyToOne @MapsId("employeeId")
    private UserEntity employee;

    @Column(nullable = false)
    private long salary;    // In cents

    public PayrollEntity(PayrollCM payroll, UserEntity user) {
        this.payrollId = new PayrollPK(user.getPublicId(), payroll.getPeriod());
        this.employee = user;
        this.salary = payroll.getSalary();;
    }
}
