package com.aman.springverse.service;

import com.aman.springverse.dto.*;
import com.aman.springverse.dto.mapper.StudentMapper;
import com.aman.springverse.entity.students.Student;
import com.aman.springverse.exception.DuplicateResourceException;
import com.aman.springverse.exception.ResourceNotFoundException;
import com.aman.springverse.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository,
                          StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public CreateResponseDto createStudent(CreateRequestDto createRequestDto) {
        boolean isEmailExists = studentRepository.existsByEmail(createRequestDto.getEmail());
        if (isEmailExists) {
            throw new DuplicateResourceException("Email already exists");
        }
        Student needToSave = studentMapper.mapToStudentEntity(createRequestDto);
        Student savedStudent = studentRepository.save(needToSave);
        return studentMapper.mapToCreateResponseDto(savedStudent);
    }

    public List<StudentDto> getAllStudents() {
        List<Student> lists = studentRepository.findAll();
        return lists
                .stream()
                .map(studentMapper::mapToStudentDto)
                .toList();
    }

    public StudentDto getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) throw new ResourceNotFoundException("Student not found");
        return studentMapper.mapToStudentDto(studentRepository.findById(id).get());
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

        return studentMapper.mapToStudentUpdateResponseDto(savedStudent);
    }

    public StudentDto deleteStudent(Long id) {
        Student existingStudent = studentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        studentRepository.deleteById(id);
        return studentMapper.mapToStudentDto(existingStudent);
    }
}
