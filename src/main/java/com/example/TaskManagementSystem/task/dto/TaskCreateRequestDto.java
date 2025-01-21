package com.example.TaskManagementSystem.task.dto;

import com.example.TaskManagementSystem.task.model.Priority;
import com.example.TaskManagementSystem.task.model.Status;
import com.example.TaskManagementSystem.task.model.Task;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for {@link Task}
 */
public record TaskCreateRequestDto(
        @NotEmpty
        String title,
        @NotEmpty
        String description,
        @NotEmpty
        String status,
        @NotEmpty
        String priority,
        @NotNull
        Long authorId,

        Long assigneeId) {
}