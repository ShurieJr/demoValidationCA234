package shuuriye.demovalidationca234.DTO;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
public class createRequestDto {
    private String employeeId;
    private String name;
    private String  email;
    private String       department;
    private double salary;
    private String   nationalId;
    private String  bankAccountNumber;
    private String   performanceRating;
}
