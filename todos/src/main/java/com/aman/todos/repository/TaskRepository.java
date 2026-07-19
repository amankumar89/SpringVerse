package com.aman.todos.repository;


import com.aman.todos.entity.Status;
import com.aman.todos.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existsByTitle(String title);

    List<Task> findByStatusNot(Status status);
}
