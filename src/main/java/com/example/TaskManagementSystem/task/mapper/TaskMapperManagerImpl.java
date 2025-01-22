package com.example.TaskManagementSystem.task.mapper;

import com.example.TaskManagementSystem.task.dto.TaskAdminUpdateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskCreateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskResponseDto;
import com.example.TaskManagementSystem.task.model.Task;
import com.example.TaskManagementSystem.task.dto.TaskUserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class TaskMapperManagerImpl implements TaskMapperManager {

    private final TaskMapper taskMapper;

    @Override
    public Task toModel(TaskCreateRequestDto dto) {
        return taskMapper.toEntity(dto);
    }

    @Override
    public Task toModel(TaskAdminUpdateRequestDto dto) {
        return taskMapper.toEntity(dto);
    }

    @Override
    public Task toModel(TaskUserUpdateRequestDto dto) {
        return taskMapper.toEntity(dto);
    }

    @Override
    public TaskResponseDto toDto(Task model) {
        return taskMapper.toTaskResponseDto(model);
    }
}
