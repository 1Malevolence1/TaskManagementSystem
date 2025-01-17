package com.example.TaskManagementSystem.comment.service;

import com.example.TaskManagementSystem.comment.dto.CommentResponseDto;
import com.example.TaskManagementSystem.comment.dto.CommentCreasteRequestDto;

import java.util.List;

public interface CommentService {

    void create(CommentCreasteRequestDto dto);
    void delete();
    List<CommentResponseDto> getAllByIdTask(Long taskId);
}
