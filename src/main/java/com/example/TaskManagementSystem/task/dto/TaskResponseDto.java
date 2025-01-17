package com.example.TaskManagementSystem.task.dto;

import com.example.TaskManagementSystem.comment.dto.CommentResponseDto;
import com.example.TaskManagementSystem.comment.model.Comment;
import com.example.TaskManagementSystem.task.model.Priority;
import com.example.TaskManagementSystem.task.model.Status;

import java.util.List;

/**
 * DTO for {@link com.example.TaskManagementSystem.task.model.Task}
 */
public record TaskResponseDto(Long id, String title, String description, Status status, Priority priority,
                              Long authorId, Long assigneeId, List<CommentResponseDto> comments) {
}