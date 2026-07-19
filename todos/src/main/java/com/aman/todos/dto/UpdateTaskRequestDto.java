package com.aman.todos.dto;

import com.aman.todos.entity.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskRequestDto {
    @NotNull(message = "Status is required")
    private Status status;
}