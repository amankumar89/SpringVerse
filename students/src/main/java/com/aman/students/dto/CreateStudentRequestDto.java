package com.aman.students.dto;

import com.aman.students.entity.CourseEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentRequestDto {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 150, message = "Email must not exceed 150 characters")
    private String email;

    @NotNull(message = "Course is required")
    private CourseEnum course;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age must be at least 16")
    @Max(value = 80, message = "Age must not exceed 100")
    private int age;
}
