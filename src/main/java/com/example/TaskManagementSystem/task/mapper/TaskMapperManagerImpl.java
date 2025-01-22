package com.example.TaskManagementSystem.task.mapper;

import com.example.TaskManagementSystem.task.dto.TaskAdminUpdateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskCreateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskResponseDto;
import com.example.TaskManagementSystem.task.model.Task;
import com.example.TaskManagementSystem.task.dto.TaskUserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class TaskMapperManagerImpl implements TaskMapperManager {

    private final TaskMapper taskMapper;
    private final TaskMapperList taskMapperList;
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


    @Override
    public List<TaskResponseDto> toDtoList(Page<Task> model) {
        return taskMapperList.toDto(model);
    }
}
