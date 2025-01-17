package com.example.TaskManagementSystem.task.mapper;

import com.example.TaskManagementSystem.task.dto.TaskCreateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskResponseDto;
import com.example.TaskManagementSystem.task.dto.TaskUpdateRequestDto;
import com.example.TaskManagementSystem.task.model.Task;

import java.awt.print.Book;

public interface TaskMapperManager {

    Task toModel (TaskCreateRequestDto dto);
    Task toModel (TaskUpdateRequestDto dto);
    TaskResponseDto toDto(Task model);
}
