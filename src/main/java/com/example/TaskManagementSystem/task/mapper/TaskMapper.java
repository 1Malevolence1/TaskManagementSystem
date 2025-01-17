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

    @Mapping(target = "author.id", source = "authorId")
    @Mapping(target = "assignee.id", source = "assigneeId")
    Task toEntity(TaskCreateRequestDto taskCreateRequestDto);

    TaskCreateRequestDto toTaskCreateRequestDto(Task task);

    Task toEntity(TaskUpdateRequestDto taskUpdateRequestDto);

    TaskUpdateRequestDto toTaskUpdateRequestDto(Task task);


    TaskResponseDto toTaskResponseDto(Task task);


}