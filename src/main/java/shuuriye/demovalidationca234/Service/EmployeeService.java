package shuuriye.demovalidationca234.Service;

import org.springframework.stereotype.Service;
import shuuriye.demovalidationca234.DTO.EmployeeResponse;
import shuuriye.demovalidationca234.DTO.createRequestDto;
import shuuriye.demovalidationca234.DTO.updateRequestDto;
import shuuriye.demovalidationca234.Model.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EmployeeService {
    //storage
    private Map<String, Employee> employees = new ConcurrentHashMap<>();
    private AtomicLong idGenerator = new AtomicLong();

    //operations
        public EmployeeResponse addEmployee(createRequestDto employeeRequest){
        String empId = "EMP" + idGenerator.incrementAndGet() ;

        //convert dto to model (entity)
        Employee newEmployee = new Employee(
                empId,
                employeeRequest.getName(),
                employeeRequest.getEmail(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary(),
                employeeRequest.getNationalId(),
                employeeRequest.getBankAccountNumber(),
                employeeRequest.getPerformanceRating()
        );
        employees.put(newEmployee.getEmployeeId(), newEmployee);
       return EmployeeResponse.from(newEmployee);
    }
    public EmployeeResponse getEmployee(String employeeId){
        return EmployeeResponse.from(employees.get(employeeId));
    }
    public void removeEmployee(String employeeId){
        employees.remove(employeeId);
    }

    public Collection<EmployeeResponse> getAllEmployees(){
        return EmployeeResponse.from(employees.values());
    }
    public void updateEmployee(updateRequestDto employeeRequest){
            Employee updatedEmp = employees.get(employeeRequest.getEmployeeId());
            //conversion dto -> entity

            updatedEmp.setName(employeeRequest.getName());
            updatedEmp.setEmail(employeeRequest.getEmail());
            updatedEmp.setDepartment(employeeRequest.getDepartment());

        employees.put(employeeRequest.getEmployeeId(), updatedEmp);
    }

}
