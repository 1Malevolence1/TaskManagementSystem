package com.example.TaskManagementSystem.comment.mapper;

import com.example.TaskManagementSystem.comment.dto.CommentCreateRequestDto;
import com.example.TaskManagementSystem.comment.dto.CommentResponseDto;
import com.example.TaskManagementSystem.comment.model.Comment;

import java.util.List;

public interface CommentMapperManager {

    List<CommentResponseDto> toDto(List<Comment> model);
    Comment toModel(CommentCreateRequestDto dto);
}
