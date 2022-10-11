package account.services;

import account.contracts.PayrollCM;
import account.contracts.response.PayrollResponseCM;
import account.models.PayrollEntity;
import account.models.PayrollPK;
import account.models.UserEntity;
import account.repositories.PayrollDAO;
import account.repositories.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PayrollService {

    @Autowired
    UsersDAO usersDAO;

    @Autowired
    PayrollDAO payrollDao;

    public PayrollResponseCM getPayrollEntry(String period, UserEntity employee) {
        PayrollPK payrollKey = new PayrollPK(employee.getPublicId(), period);
        Optional<PayrollEntity> payrollOptional = payrollDao.findById(payrollKey);
        PayrollEntity payrollEntity = payrollOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payroll entry not found"));

        return new PayrollResponseCM(payrollEntity, employee);
    }

    public List<PayrollResponseCM> getPayrollEntries(UserEntity employee) {
        List<PayrollResponseCM> response = new ArrayList<>();
        List<PayrollEntity> payrollRecords = payrollDao.findAllByPayrollId_EmployeeIdOrderByPayrollId_PeriodDesc(employee.getPublicId());

        for(PayrollEntity payrollRecord : payrollRecords)
            response.add(new PayrollResponseCM(payrollRecord, employee));

        return response;
    }

    @Transactional
    public void addPayrollEntries(List<PayrollCM> payrolls) {
        for(PayrollCM payroll : payrolls) {
            UserEntity employee = usersDAO.findByUsername(payroll.getEmployeeEmail());
            if(employee==null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee not found");

            PayrollPK payrollKey = new PayrollPK(employee.getPublicId(), payroll.getPeriod());
            Optional<PayrollEntity> payrollOptional = payrollDao.findById(payrollKey);
            if(payrollOptional.isPresent())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payroll entry already exists");

            PayrollEntity payrollEntity = new PayrollEntity(payroll, employee);
            payrollDao.save(payrollEntity);
        }
    }

    public void updatePayrollEntry(PayrollCM payroll) {
        UserEntity employee = usersDAO.findByUsername(payroll.getEmployeeEmail());
        if(employee==null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee not found");

        PayrollPK payrollKey = new PayrollPK(employee.getPublicId(), payroll.getPeriod());
        Optional<PayrollEntity> payrollOptional = payrollDao.findById(payrollKey);
        PayrollEntity payrollEntity = payrollOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payroll entry not found"));
        payrollEntity.setSalary(payroll.getSalary());
        payrollDao.save(payrollEntity);
    }
}
