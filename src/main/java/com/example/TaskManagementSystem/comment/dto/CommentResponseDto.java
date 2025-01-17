package com.example.TaskManagementSystem.comment.dto;

import com.example.TaskManagementSystem.account.dto.AccountResponseDto;
import com.example.TaskManagementSystem.task.dto.TaskResponseDto;

/**
 * DTO for {@link com.example.TaskManagementSystem.comment.model.Comment}
 */
public record CommentResponseDto(Long id, String text, Long taskId, Long accountId) {
}