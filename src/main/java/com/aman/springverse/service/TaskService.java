package com.aman.springverse.service;

import com.aman.springverse.dto.tasks.*;
import com.aman.springverse.entity.tasks.Status;
import com.aman.springverse.entity.tasks.Task;
import com.aman.springverse.exception.DuplicateResourceException;
import com.aman.springverse.exception.ResourceNotFoundException;
import com.aman.springverse.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public CreateTaskResponseDto createTask(CreateTaskRequestDto requestDto) {
        if (taskRepository.existsByTitle(requestDto.getTitle())) {
            throw new DuplicateResourceException("Task with title '" + requestDto.getTitle() + "' already exists");
        }
        Task task = modelMapper.map(requestDto, Task.class);
        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask, CreateTaskResponseDto.class);
    }

    public List<TaskResponseDto> getAllTasks() {
        List<Task> taskList = taskRepository.findAll();
        return taskList
                .stream()
                .map((task) -> modelMapper.map(task, TaskResponseDto.class))
                .collect(Collectors.toList());
    }

    public TaskResponseDto getTaskById(Long id) {
        Task existingTask = taskRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
        return modelMapper.map(existingTask, TaskResponseDto.class);
    }

    @Transactional
    public UpdateTaskResponseDto updateTaskStatus(Long id, UpdateTaskRequestDto requestDto) {
        Task existingTask = taskRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));

        existingTask.setStatus(requestDto.getStatus());

        taskRepository.save(existingTask);

        return modelMapper.map(existingTask, UpdateTaskResponseDto.class);
    }

    public void deleteTask(Long id) {
        // first checking task is present in db or not
        taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
        taskRepository.deleteById(id);
    }

    public List<TaskResponseDto> getPendingTasks() {
        List<Task> tasks = taskRepository.findByStatusNot(Status.COMPLETED);
        return tasks
                .stream()
                .map((task) -> modelMapper.map(task, TaskResponseDto.class))
                .collect(Collectors.toList());
    }
}
