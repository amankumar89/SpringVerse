package com.aman.springverse.repository;

import com.aman.springverse.entity.tasks.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
