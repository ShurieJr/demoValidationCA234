package shuuriye.demovalidationca234.Service;

import org.springframework.stereotype.Service;
import shuuriye.demovalidationca234.DTO.EmployeeResponse;
import shuuriye.demovalidationca234.DTO.createRequestDto;
import shuuriye.demovalidationca234.Model.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmployeeService {
    //storage
    private Map<String, Employee> employees = new ConcurrentHashMap<>();

    //operations
    public EmployeeResponse addEmployee(createRequestDto employeeRequest){
        //convert dto to model (entity)
        Employee newEmployee = new Employee(
                employeeRequest.getEmployeeId(),
                employeeRequest.getName(),
                employeeRequest.getEmail(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary(),
                employeeRequest.getNationalId(),
                employeeRequest.getBankAccountNumber(),
                employeeRequest.getPerformanceRating()
        );
       return EmployeeResponse.from(employees.put(newEmployee.getEmployeeId(), newEmployee));
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
    public void updateEmployee(String employeeId , Employee employee){
        employees.put(employeeId, employee);
    }

}
