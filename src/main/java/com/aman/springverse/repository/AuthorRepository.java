package com.aman.springverse.repository;

import com.aman.springverse.entity.books.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByEmail(String email);

    boolean existsByEmail(String email);
}
