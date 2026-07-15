package com.aman.springverse.service;

import com.aman.springverse.dto.PageResponseDto;
import com.aman.springverse.dto.employees.*;
import com.aman.springverse.entity.employees.Employee;
import com.aman.springverse.exception.DuplicateResourceException;
import com.aman.springverse.exception.ResourceNotFoundException;
import com.aman.springverse.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            ModelMapper modelMapper
    ) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public CreateEmployeeResponseDto createEmployee(CreateEmployeeRequestDto employeeBody) {
        Optional<Employee> employeeExists = employeeRepository
                .findByEmail(employeeBody.getEmail());
        if (employeeExists.isPresent()) {
            throw new DuplicateResourceException("Email already exists");
        }
        Employee needToSave = modelMapper.map(employeeBody, Employee.class);

        Employee savedEmployee = employeeRepository.save(needToSave);
        return modelMapper.map(savedEmployee, CreateEmployeeResponseDto.class);
    }

    public PageResponseDto<EmployeeDto> getAllEmployees(
            int page, int size, String sortBy, String orderBy
    ) {
        Sort sort = orderBy.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);

        List<EmployeeDto> employeeLists = employeePage
                .getContent()
                .stream()
                .map((employee -> modelMapper.map(employee, EmployeeDto.class)))
                .toList();

        return PageResponseDto
                .<EmployeeDto>builder()
                .content(employeeLists)
                .size(employeePage.getSize())
                .total(employeePage.getTotalElements())
                .totalPages(employeePage.getTotalPages())
                .build();
    }

    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Transactional
    public UpdateEmployeeResponseDto updateEmployee(
            Long id, UpdateEmployeeRequestDto updateRequestDto) {
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employee.setName(updateRequestDto.getName());
        employee.setAge(updateRequestDto.getAge());

        return modelMapper.map(employee, UpdateEmployeeResponseDto.class);
    }

    public EmployeeDto deleteEmployee(Long id) {
        Employee existingEmployee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        employeeRepository.deleteById(id);
        return modelMapper.map(existingEmployee, EmployeeDto.class);
    }
}
