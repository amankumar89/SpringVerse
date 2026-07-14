package com.aman.springverse.service;

import com.aman.springverse.dto.*;
import com.aman.springverse.entity.students.Student;
import com.aman.springverse.exception.DuplicateResourceException;
import com.aman.springverse.exception.ResourceNotFoundException;
import com.aman.springverse.repository.StudentRepository;
import org.modelmapper.ModelMapper;
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

    public CreateResponseDto createStudent(CreateRequestDto createRequestDto) {
        boolean isEmailExists = studentRepository.existsByEmail(createRequestDto.getEmail());
        if (isEmailExists) {
            throw new DuplicateResourceException("Email already exists");
        }
        Student needToSave = modelMapper.map(createRequestDto, Student.class);

        Student savedStudent = studentRepository.save(needToSave);
        return modelMapper.map(savedStudent, CreateResponseDto.class);
    }

    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students
                .stream()
                .map((student) -> modelMapper.map(student, StudentDto.class))
                .toList();
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
