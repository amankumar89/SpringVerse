package com.aman.springverse.repository;

import com.aman.springverse.entity.books.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByEmail(String email);

    boolean existsByEmail(String email);
}
