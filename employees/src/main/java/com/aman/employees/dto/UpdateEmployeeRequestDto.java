package com.aman.employees.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeRequestDto {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be positive")
    private Double salary;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 115, message = "Age must be less than 100")
    private int age;

}
