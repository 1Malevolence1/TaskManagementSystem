package com.example.TaskManagementSystem.task.mapper;

import com.example.TaskManagementSystem.task.dto.TaskCreateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskResponseDto;
import com.example.TaskManagementSystem.task.dto.TaskUpdateRequestDto;
import com.example.TaskManagementSystem.task.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class TaskMapperManagerImpl implements TaskMapperManager {

    private TaskMapper taskMapper;

    @Override
    public Task toModel(TaskCreateRequestDto dto) {
        return taskMapper.toEntity(dto);
    }

    @Override
    public Task toModel(TaskUpdateRequestDto dto) {
        return taskMapper.toEntity(dto);
    }

    @Override
    public TaskResponseDto toDto(Task model) {
        return taskMapper.toBookResponseDto(model);
    }
}
