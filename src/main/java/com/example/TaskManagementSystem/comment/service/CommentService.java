package com.example.TaskManagementSystem.comment.service;

import com.example.TaskManagementSystem.comment.dto.CommentCreateRequestDto;
import com.example.TaskManagementSystem.comment.dto.CommentResponseDto;

import java.util.List;


public interface CommentService {

    void create(CommentCreateRequestDto dto);
    void delete(Long id);
    List<CommentResponseDto> getAllByTaskId(Long taskId);
}
