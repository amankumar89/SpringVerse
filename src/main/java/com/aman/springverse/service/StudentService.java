package com.aman.springverse.service;

import com.aman.springverse.dto.PageResponseDto;
import com.aman.springverse.dto.students.*;
import com.aman.springverse.entity.students.Student;
import com.aman.springverse.exception.DuplicateResourceException;
import com.aman.springverse.exception.ResourceNotFoundException;
import com.aman.springverse.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentService(
            StudentRepository studentRepository,
            ModelMapper modelMapper
    ) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public CreateStudentResponseDto createStudent(CreateStudentRequestDto createStudentRequestDto) {
        boolean isEmailExists = studentRepository.existsByEmail(createStudentRequestDto.getEmail());
        if (isEmailExists) {
            throw new DuplicateResourceException("Email already exists");
        }
        Student needToSave = modelMapper.map(createStudentRequestDto, Student.class);

        Student savedStudent = studentRepository.save(needToSave);
        return modelMapper.map(savedStudent, CreateStudentResponseDto.class);
    }

    public PageResponseDto<StudentDto> getAllStudents(
            int page, int size, String sortBy, String orderBy
    ) {
        Sort sort = orderBy.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Student> studentPage = studentRepository.findAll(pageable);

        List<StudentDto> studentsList = studentPage
                .getContent()
                .stream()
                .map((student -> modelMapper.map(student, StudentDto.class)))
                .toList();

        return PageResponseDto
                .<StudentDto>builder()
                .content(studentsList)
                .size(studentPage.getSize())
                .total(studentPage.getTotalElements())
                .totalPages(studentPage.getTotalPages())
                .build();
    }

    public StudentDto getStudentById(Long id) {
        Student student = studentRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        return modelMapper.map(student, StudentDto.class);
    }

    public UpdateStudentResponseDto updateStudent(
            Long id, UpdateStudentRequestDto updateRequestDto) {
        Student student = studentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        student.setName(updateRequestDto.getName());
        student.setEmail(updateRequestDto.getEmail());
        student.setAge(updateRequestDto.getAge());
        Student savedStudent = studentRepository.save(student);

        return modelMapper.map(savedStudent, UpdateStudentResponseDto.class);
    }

    public StudentDto deleteStudent(Long id) {
        Student existingStudent = studentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        studentRepository.deleteById(id);
        return modelMapper.map(existingStudent, StudentDto.class);
    }
}
