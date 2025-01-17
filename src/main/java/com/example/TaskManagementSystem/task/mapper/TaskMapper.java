package com.example.TaskManagementSystem.task.mapper;

import com.example.TaskManagementSystem.account.mapper.AccountMapper;
import com.example.TaskManagementSystem.comment.dto.CommentResponseDto;
import com.example.TaskManagementSystem.comment.model.Comment;
import com.example.TaskManagementSystem.task.dto.TaskCreateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskResponseDto;
import com.example.TaskManagementSystem.task.dto.TaskUpdateRequestDto;
import com.example.TaskManagementSystem.task.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {TaskMapper.class, AccountMapper.class})
public interface TaskMapper {
    Task toEntity(TaskCreateRequestDto bookCreateRequestDto);

    TaskCreateRequestDto toBookCreateRequestDto(Task task);

    Task toEntity(TaskUpdateRequestDto bookUpdateRequestDto);

    TaskUpdateRequestDto toBookUpdateRequestDto(Task task);

    Task toEntity(TaskResponseDto bookResponseDto);

    TaskResponseDto toBookResponseDto(Task task);

    @Mapping(source = "accountId", target = "account.id")
    @Mapping(source = "taskId", target = "task.id")
    @Mapping(source = "taskId", target = "task.id")
    Comment toEntity(CommentResponseDto commentResponseDto);

    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "task.id", target = "taskId")
    @Mapping(source = "task.id", target = "taskId")
    CommentResponseDto toCommentResponseDto(Comment comment);


}