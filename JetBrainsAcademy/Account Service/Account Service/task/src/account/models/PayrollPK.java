package account.models;

import account.util.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Embeddable
@NoArgsConstructor
public class PayrollPK implements Serializable {
    private long employeeId;
    private LocalDate period;   // Convert to/from format: mm-YYYY e.g. 07-2022

    public PayrollPK(long userId, String period) {
        this.employeeId = userId;
        this.period = DateUtil.mmYYYYToLocalDate(period);
    }
}
