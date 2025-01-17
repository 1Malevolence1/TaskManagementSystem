package com.example.TaskManagementSystem.task.dto;

import com.example.TaskManagementSystem.task.model.Priority;
import com.example.TaskManagementSystem.task.model.Status;

/**
 * DTO for {@link com.example.TaskManagementSystem.task.model.Task}
 */
public record TaskUpdateRequestDto(Long id, String title, String description, Status status, Priority priority,
                                   Long authorId, Long assigneeId) {
}