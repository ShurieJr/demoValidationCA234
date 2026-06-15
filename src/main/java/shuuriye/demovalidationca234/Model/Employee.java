package shuuriye.demovalidationca234.Model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {   //model
   private String employeeId;
    private String name;
    private String  email;
    private String  department;

    private double salary;
    private String   nationalId;
    private String  bankAccountNumber;
         private String   performanceRating;

}
