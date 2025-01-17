package com.example.TaskManagementSystem.comment.mapper;


import com.example.TaskManagementSystem.comment.dto.CommentResponseDto;
import com.example.TaskManagementSystem.comment.model.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = CommentMapper.class)
public interface CommentMapperList {

    List<CommentResponseDto> toDto(List<Comment> model);
}
