package com.example.TaskManagementSystem.task.mapper;

import com.example.TaskManagementSystem.task.dto.TaskCreateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskResponseDto;
import com.example.TaskManagementSystem.task.dto.TaskAdminUpdateRequestDto;
import com.example.TaskManagementSystem.task.model.Task;
import com.example.TaskManagementSystem.task.dto.TaskUserUpdateRequestDto;

public interface TaskMapperManager {

    Task toModel (TaskCreateRequestDto dto);
    Task toModel (TaskAdminUpdateRequestDto dto);
    Task toModel (TaskUserUpdateRequestDto dto);
    TaskResponseDto toDto(Task model);
}
