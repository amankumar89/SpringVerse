package com.aman.springverse.repository;

import com.aman.springverse.entity.tasks.Status;
import com.aman.springverse.entity.tasks.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existsByTitle(String title);

    List<Task> findByStatusNot(Status status);
}
