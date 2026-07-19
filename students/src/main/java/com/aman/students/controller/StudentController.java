package com.aman.springverse.controller;

import com.aman.springverse.dto.PageResponseDto;
import com.aman.springverse.dto.students.*;
import com.aman.springverse.service.StudentService;
import com.aman.springverse.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateStudentResponseDto>> createStudent(
            @RequestBody CreateStudentRequestDto createStudentRequestDto
    ) {
        return ApiResponse.created(
                "Student created successfully",
                studentService.createStudent(createStudentRequestDto)
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponseDto<StudentDto>>> getAllStudent(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(defaultValue = "createdAt", required = false) String sortBy,
            @RequestParam(defaultValue = "desc", required = false) String orderBy
    ) {
        return ApiResponse.ok(
                "All Students fetched successfully",
                studentService.getAllStudents(page, size, sortBy, orderBy)
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
                "Student updated successfully",
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
