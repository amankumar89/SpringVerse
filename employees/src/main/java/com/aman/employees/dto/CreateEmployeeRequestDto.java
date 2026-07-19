package com.aman.employees.dto;

import com.aman.employees.entity.Department;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeRequestDto {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Department is required")
    @Enumerated(EnumType.STRING)
    private Department department;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be positive")
    private Double salary;

    @NotNull(message = "Joining date is required")
    private LocalDateTime joiningDate;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 115, message = "Age must be less than 100")
    private int age;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
