package com.example.TaskManagementSystem.comment.service;

import com.example.TaskManagementSystem.comment.dto.CommentCreateRequestDto;
import com.example.TaskManagementSystem.comment.dto.CommentResponseDto;

import java.util.List;


public interface CommentService {

    void create(CommentCreateRequestDto dto, String token);
    void delete(Long id, String token);
    List<CommentResponseDto> getAllByTaskId(Long taskId);
}
