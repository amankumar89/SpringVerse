package com.aman.springverse.controller;

import com.aman.springverse.dto.PageResponseDto;
import com.aman.springverse.service.EmployeeService;
import com.aman.springverse.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateEmployeeResponseDto>> createEmployee(@RequestBody CreateEmployeeRequestDto employeeBodyDto) {
        return ApiResponse.created(
                "Employee created successfully",
                employeeService.createEmployee(employeeBodyDto)
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponseDto<EmployeeDto>>> getAllEmployees(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(defaultValue = "createdAt", required = false) String sortBy,
            @RequestParam(defaultValue = "desc", required = false) String orderBy
    ) {
        return ApiResponse.ok(employeeService.getAllEmployees(page, size, sortBy, orderBy));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDto>> getEmployeeById(@PathVariable Long id) {
        return ApiResponse.ok(employeeService.getEmployeeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UpdateEmployeeResponseDto>> getEmployeeById(
            @PathVariable Long id,
            @RequestBody UpdateEmployeeRequestDto employeeBodyDto
    ) {
        return ApiResponse.ok(employeeService.updateEmployee(id, employeeBodyDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDto>> deleteEmployee(@PathVariable Long id) {
        return ApiResponse.ok(employeeService.deleteEmployee(id));
    }
}
