package com.example.TaskManagementSystem.comment.service;

import com.example.TaskManagementSystem.comment.dto.CommentCreateRequestDto;
import com.example.TaskManagementSystem.comment.dto.CommentResponseDto;

import java.util.List;


public interface CommentService {

    void create(CommentCreateRequestDto dto, Long accountId, String role);
    void delete(Long id, Long accountId);
    List<CommentResponseDto> getAllByTaskId(Long taskId);
}
