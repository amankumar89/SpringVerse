package com.aman.springverse.dto.tasks;

import com.aman.springverse.entity.tasks.Priority;
import com.aman.springverse.entity.tasks.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskResponseDto {
    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDateTime dueDate;
    private LocalDateTime updatedAt;
}
