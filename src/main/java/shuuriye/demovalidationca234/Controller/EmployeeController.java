package shuuriye.demovalidationca234.Controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import shuuriye.demovalidationca234.DTO.EmployeeResponse;
import shuuriye.demovalidationca234.DTO.createRequestDto;
import shuuriye.demovalidationca234.Model.Employee;
import shuuriye.demovalidationca234.Service.EmployeeService;

import java.util.Collection;

@RestController   //JSON FORMAT
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    //requests -- endpoints
    @GetMapping("/all")
    public Collection<EmployeeResponse> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("{id}")
    public EmployeeResponse getEmployee(@PathVariable String id){
        return employeeService.getEmployee(id);
    }

    @PostMapping
    public EmployeeResponse createEmployee(@Valid
                                               @RequestBody createRequestDto employee){
        return employeeService.addEmployee(employee);
    }
//    @PutMapping("{employeeId}")
//    public void updateEmployee(@PathVariable String employeeId ,  @RequestBody Employee employee){
//        employeeService.updateEmployee(employee.getEmployeeId(), employee);
//    }
}
