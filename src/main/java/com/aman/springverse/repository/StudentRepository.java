package com.aman.springverse.repository;

import com.aman.springverse.dto.StudentDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentDto, Long> {
}
