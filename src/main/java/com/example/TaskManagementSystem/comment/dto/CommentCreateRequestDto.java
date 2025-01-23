package com.example.TaskManagementSystem.comment.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for {@link com.example.TaskManagementSystem.comment.model.Comment}
 */
public record CommentCreateRequestDto(
        @NotEmpty
        String text,
        @NotNull
        Long taskId) {
}