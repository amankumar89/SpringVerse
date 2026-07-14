package com.aman.springverse.controller;

import com.aman.springverse.dto.*;
import com.aman.springverse.service.StudentService;
import com.aman.springverse.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateResponseDto>> createStudent(
            @RequestBody CreateRequestDto createRequestDto
    ) {
        return ApiResponse.created(
                "Student created successfully",
                studentService.createStudent(createRequestDto)
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentDto>>> getStudent() {
        return ApiResponse.ok(
                "All Students fetched successfully",
                studentService.getAllStudents()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDto>> getStudentById(
            @PathVariable Long id
    ) {
        return ApiResponse.ok(
                "Student fetched successfully",
                studentService.getStudentById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UpdateStudentResponseDto>> createStudent(
            @PathVariable Long id,
            @RequestBody UpdateStudentRequestDto updateRequestDto
    ) {
        return ApiResponse.ok(
                "Student created successfully",
                studentService.updateStudent(id, updateRequestDto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDto>> deleteStudent(
            @PathVariable Long id
    ) {
        return ApiResponse.ok(
                "Student deleted successfully",
                studentService.deleteStudent(id)
        );
    }
}
