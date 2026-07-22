package com.aman.students.dto;

import com.aman.students.entity.CourseEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentResponseDto {
    private Long id;
    private String name;
    private String email;
    private CourseEnum course;
    private int age;
    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
}
