package account.repositories;

import account.models.PayrollEntity;
import account.models.PayrollPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PayrollDAO extends CrudRepository<PayrollEntity, PayrollPK> {
    List<PayrollEntity> findAllByPayrollId_EmployeeIdOrderByPayrollId_PeriodDesc(long employeeId);
}
