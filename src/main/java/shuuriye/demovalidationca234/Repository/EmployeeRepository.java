package shuuriye.demovalidationca234.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shuuriye.demovalidationca234.Model.Employee;

@Repository
public interface EmployeeRepository
        extends JpaRepository<Employee, String> {
}
