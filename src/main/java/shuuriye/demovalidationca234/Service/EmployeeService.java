//package shuuriye.demovalidationca234.Service;
//
//
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Service;
//import shuuriye.demovalidationca234.DTO.EmployeeResponse;
//import shuuriye.demovalidationca234.DTO.createRequestDto;
//import shuuriye.demovalidationca234.DTO.updateRequestDto;
//import shuuriye.demovalidationca234.Model.Employee;
//
//import java.util.Collection;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicLong;
//
//@Service
//public class EmployeeService {
//    //jdbcTemplate   ?
//    private final JdbcTemplate jdbctemplate;
//    private final NamedParameterJdbcTemplate namedJdbc;
//    //storage
////    private Map<String, Employee> employees = new ConcurrentHashMap<>();
//    private AtomicLong idGenerator = new AtomicLong();
//
//    public EmployeeService(JdbcTemplate jdbctemplate, NamedParameterJdbcTemplate namedJdbc) {
//        this.jdbctemplate = jdbctemplate;
//        this.namedJdbc = namedJdbc;
//    }
//
//
//    //operations
//        public EmployeeResponse addEmployee(createRequestDto employeeRequest){
//        String sqlQuery = """
//                  insert into employee values(
//                   :id ,
//                   :name ,
//                   :email ,
//                   :department,
//                   :salary,
//                   :nationalId,
//                   :bankAccountNumber,
//                   :performanceRating)
//                """;
//
//        String empId = "EMP" + idGenerator.incrementAndGet() ;
//
////        convert dto to model (entity)
//        Employee newEmployee = new Employee(
//                empId,
//                employeeRequest.getName(),
//                employeeRequest.getEmail(),
//                employeeRequest.getDepartment(),
//                employeeRequest.getSalary(),
//                employeeRequest.getNationalId(),
//                employeeRequest.getBankAccountNumber(),
//                employeeRequest.getPerformanceRating()
//        );
//       MapSqlParameterSource params = new MapSqlParameterSource()
//               .addValue("salary" , employeeRequest.getSalary() )
//               .addValue("nationalId" , employeeRequest.getNationalId() )
//               .addValue("bankAccountNumber" , employeeRequest.getBankAccountNumber() )
//               .addValue("performanceRating" ,  employeeRequest.getPerformanceRating())
//               .addValue("id" , empId)
//               .addValue("name" ,employeeRequest.getName() )
//               .addValue("email" , employeeRequest.getEmail())
//               .addValue("department" , employeeRequest.getDepartment());
//
//       namedJdbc.update(sqlQuery , params);
//       return EmployeeResponse.from(newEmployee);
//    }
//    public EmployeeResponse getEmployee(String employeeId) {
//        String sqlQuery = "select * from employee where \"employeeId\" = ?";
//        Employee result=  jdbctemplate.queryForObject(sqlQuery,
//                new Object[]{employeeId},
//                new BeanPropertyRowMapper<>(Employee.class));
//        return EmployeeResponse.from(result);
//    }
//    public void removeEmployee(String employeeId){
//        String sql = "DELETE FROM employee WHERE \"employeeId\" = :id";
//        MapSqlParameterSource params = new MapSqlParameterSource()
//                .addValue("id" , employeeId);
//
//        namedJdbc.update(sql , params );
//
////        employees.remove(employeeId);
//    }
//
//    public Collection<EmployeeResponse> getAllEmployees(){
//        String sqlQuery = "select * from employee";
//      Collection<Employee> result=  jdbctemplate.query(sqlQuery,
//                new BeanPropertyRowMapper<>(Employee.class));
//      return EmployeeResponse.from(result);
//
//    }
//    public void updateEmployee(updateRequestDto employeeRequest){
////            Employee updatedEmp = employees.get(employeeRequest.getEmployeeId());
////            //conversion dto -> entity
////
////            updatedEmp.setName(employeeRequest.getName());
////            updatedEmp.setEmail(employeeRequest.getEmail());
////            updatedEmp.setDepartment(employeeRequest.getDepartment());
////
////        employees.put(employeeRequest.getEmployeeId(), updatedEmp);
//        String sqlQuery = """
//                update employee set name=:name ,
//                email = :email,
//                department = :dept
//                where \"employeeId\" = :id
//                """;
//        MapSqlParameterSource params = new MapSqlParameterSource()
//                .addValue("id" ,employeeRequest.getEmployeeId() )
//                .addValue("name" , employeeRequest.getName())
//                .addValue("email" ,  employeeRequest.getEmail())
//                .addValue("dept" , employeeRequest.getDepartment());
//        namedJdbc.update(sqlQuery , params);
//    }
//
//}

package shuuriye.demovalidationca234.Service;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import shuuriye.demovalidationca234.DTO.EmployeeResponse;
import shuuriye.demovalidationca234.DTO.createRequestDto;
import shuuriye.demovalidationca234.DTO.updateRequestDto;
import shuuriye.demovalidationca234.Model.Employee;
import shuuriye.demovalidationca234.Repository.EmployeeRepository;

import java.util.Collection;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EmployeeService {
   private final EmployeeRepository repo;
    private AtomicLong idGenerator = new AtomicLong();

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }


    //operations
    public void addEmployee(createRequestDto employeeRequest){


        String empId = "EMP" + idGenerator.incrementAndGet() ;

//        convert dto to model (entity)
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


        repo.save(newEmployee);
    }
    public EmployeeResponse getEmployee(String employeeId) {
       Employee result =  repo.findById(employeeId).orElse(null);
       return EmployeeResponse.from(result);
    }
    public void removeEmployee(String employeeId){
        repo.deleteById(employeeId);
    }

    public Collection<EmployeeResponse> getAllEmployees(){

        Collection<Employee> result=  repo.findAll();
        return EmployeeResponse.from(result);

    }
    public void updateEmployee(updateRequestDto employeeRequest){
        Employee emp = repo.findById(employeeRequest.getEmployeeId()).orElse(null);

        Employee newEmployee = new Employee(
                employeeRequest.getEmployeeId(),
                employeeRequest.getName(),
                employeeRequest.getEmail(),
                employeeRequest.getDepartment(),
                emp.getSalary(),
                emp.getNationalId(),
                emp.getBankAccountNumber(),
                emp.getPerformanceRating()
        );
        repo.save(newEmployee);
    }

}
