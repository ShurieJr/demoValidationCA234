package shuuriye.demovalidationca234.DTO;

import lombok.*;
import lombok.Getter;
import shuuriye.demovalidationca234.Model.Employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeResponse {
    private String employeeId;
    private String name;
    private String  email;
    private String  department;

    public static EmployeeResponse from(Employee employee) {
        return new EmployeeResponse(
                employee.getEmployeeId(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartment()
        );
    }

    public static Collection<EmployeeResponse> from(Collection<Employee> employees) {
        List<EmployeeResponse> responses = new ArrayList<>();

        for( Employee emp : employees){
            responses.add(from(emp));
        }

        return responses;
    }


}
