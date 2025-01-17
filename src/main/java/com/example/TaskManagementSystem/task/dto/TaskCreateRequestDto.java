package com.example.TaskManagementSystem.task.dto;

import com.example.TaskManagementSystem.task.model.Priority;
import com.example.TaskManagementSystem.task.model.Status;
import com.example.TaskManagementSystem.task.model.Task;

/**
 * DTO for {@link Task}
 */
public record TaskCreateRequestDto(String title, String description, Status status, Priority priority, Long authorId,
                                   Long assigneeId) {
}