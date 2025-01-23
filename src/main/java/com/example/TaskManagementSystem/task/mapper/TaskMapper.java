package com.example.TaskManagementSystem.task.mapper;

import com.example.TaskManagementSystem.account.mapper.AccountMapper;
import com.example.TaskManagementSystem.comment.mapper.CommentMapperList;
import com.example.TaskManagementSystem.task.dto.TaskAdminUpdateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskCreateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskResponseDto;
import com.example.TaskManagementSystem.task.model.Task;
import com.example.TaskManagementSystem.task.dto.TaskUserUpdateRequestDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {TaskMapper.class, AccountMapper.class, CommentMapperList.class})
public interface TaskMapper {


    @Mapping(target = "assignee.id", source = "assigneeId")
    Task toEntity(TaskCreateRequestDto taskCreateRequestDto);

    TaskCreateRequestDto toTaskCreateRequestDto(Task task);

    Task toEntity(TaskAdminUpdateRequestDto taskAdminUpdateRequestDto);

    TaskAdminUpdateRequestDto toTaskUpdateRequestDto(Task task);


    TaskResponseDto toTaskResponseDto(Task task);


    Task toEntity(TaskUserUpdateRequestDto taskUserUpdateRequestDto);

    @InheritInverseConfiguration(name = "toEntity")
    TaskUserUpdateRequestDto toTaskUserUpdateRequestDto(Task task);
}