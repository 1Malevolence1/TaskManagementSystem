package com.example.TaskManagementSystem.task.mapper;

import com.example.TaskManagementSystem.task.dto.TaskCreateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskResponseDto;
import com.example.TaskManagementSystem.task.dto.TaskUpdateRequestDto;
import com.example.TaskManagementSystem.task.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TaskMapper {
    Task toEntity(TaskCreateRequestDto bookCreateRequestDto);

    TaskCreateRequestDto toBookCreateRequestDto(Task task);

    Task toEntity(TaskUpdateRequestDto bookUpdateRequestDto);

    TaskUpdateRequestDto toBookUpdateRequestDto(Task task);

    Task toEntity(TaskResponseDto bookResponseDto);

    TaskResponseDto toBookResponseDto(Task task);
}