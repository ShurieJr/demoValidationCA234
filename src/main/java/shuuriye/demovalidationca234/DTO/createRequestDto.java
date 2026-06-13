package shuuriye.demovalidationca234.DTO;

import jakarta.validation.constraints.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class createRequestDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String  email;

    @NotBlank(message = "Department is required")
    private String  department;


    @Positive(message = "Salary must be positive")
    private double salary;
    @NotBlank(message = "National ID is required")
    @Size(min = 10, max = 10, message = "National ID must be 10 ")
    private String nationalId;

    @NotBlank(message = "Bank Account Number is required")
    @Pattern(regexp = "^[0-9]{16}$",
            message = "Bank Account Number must be 16 digits")
    private String bankAccountNumber;

    @NotBlank(message = "Performance Rating is required")
    private String  performanceRating;


}
