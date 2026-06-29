package shuuriye.demovalidationca234.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "employee")
public class Employee {   //model
    @Id //primary key
    private String employeeId;
    @Column(nullable = false )
    private String name;
    @Column(nullable = false , unique = true)
    private String  email;
    @Column(nullable = false)
    private String  department;
    @Column(nullable = false)
    private double salary;
    @Column(nullable = false)
    private String   nationalId;
    @Column(nullable = false)
    private String  bankAccountNumber;
    @Column(nullable = false)
    private String   performanceRating;

}
