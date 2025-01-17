package com.example.TaskManagementSystem.comment.dto;

/**
 * DTO for {@link com.example.TaskManagementSystem.comment.model.Comment}
 */
public record CommentCreateRequestDto(String text, Long taskId, Long accountId) {
}