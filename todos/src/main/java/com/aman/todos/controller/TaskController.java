package com.aman.todos.controller;

import com.aman.todos.dto.*;
import com.aman.todos.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<ApiResponse<CreateTaskResponseDto>> createTask(
            @Valid @RequestBody CreateTaskRequestDto requestDto
    ) {
        return ApiResponse.created(
                "Task created successfully",
                taskService.createTask(requestDto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TaskResponseDto>>> getAllTasks() {
        return ApiResponse.ok(
                "Tasks fetched successfully",
                taskService.getAllTasks()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponseDto>> getTaskById(@PathVariable Long id) {
        return ApiResponse.ok(
                "Task fetched successfully",
                taskService.getTaskById(id)
        );
    }

    @GetMapping("/pending")
    public ResponseEntity<ApiResponse<List<TaskResponseDto>>> getPendingTasks() {
        return ApiResponse.ok(
                "Pending Tasks fetched successfully",
                taskService.getPendingTasks()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UpdateTaskResponseDto>> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTaskRequestDto requestDto) {
        return ApiResponse.ok(
                "Task status updated successfully",
                taskService.updateTaskStatus(id, requestDto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ApiResponse.noContent("Task deleted successfully");
    }
}
