package com.aman.springverse.modules.students.dto;

import com.aman.springverse.modules.students.entity.CourseEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private String id;
    private String name;
    private String email;
    private CourseEnum course;
    private int age;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
