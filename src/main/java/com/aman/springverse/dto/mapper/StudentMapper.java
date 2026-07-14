package com.aman.springverse.dto.mapper;

import com.aman.springverse.dto.CreateRequestDto;
import com.aman.springverse.dto.CreateResponseDto;
import com.aman.springverse.dto.StudentDto;
import com.aman.springverse.entity.students.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentMapper {
    public StudentDto mapToStudentDto(Student existingStudent) {
        return StudentDto
                .builder()
                .id(existingStudent.getId())
                .name(existingStudent.getName())
                .email(existingStudent.getEmail())
                .course(existingStudent.getCourse())
                .age(existingStudent.getAge())
                .createdAt(existingStudent.getCreatedAt())
                .updatedAt(existingStudent.getUpdatedAt())
                .build();
    }

    public Student mapToStudentEntity(CreateRequestDto createRequestDto) {
        return Student
                .builder()
                .name(createRequestDto.getName())
                .email(createRequestDto.getEmail())
                .course(createRequestDto.getCourse())
                .age(createRequestDto.getAge())
                .build();
    }

    public CreateResponseDto mapToCreateResponseDto(Student savedStudent) {
        return CreateResponseDto
                .builder()
                .id(savedStudent.getId())
                .name(savedStudent.getName())
                .email(savedStudent.getEmail())
                .course(savedStudent.getCourse())
                .age(savedStudent.getAge())
                .createdAt(savedStudent.getCreatedAt())
                .build();
    }
}
