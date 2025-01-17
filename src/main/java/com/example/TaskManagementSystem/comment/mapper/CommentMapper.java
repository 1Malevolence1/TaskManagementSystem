package com.example.TaskManagementSystem.comment.mapper;

import com.example.TaskManagementSystem.comment.dto.CommentCreateRequestDto;
import com.example.TaskManagementSystem.comment.model.Comment;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {
    @Mapping(source = "accountId", target = "account.id")
    @Mapping(source = "taskId", target = "task.id")
    Comment toEntity(CommentCreateRequestDto commentCreateRequestDto);

    @InheritInverseConfiguration(name = "toEntity")
    CommentCreateRequestDto toCommentCreateRequestDto(Comment comment);
}