package com.example.TaskManagementSystem.task.serivce;

import com.example.TaskManagementSystem.task.dto.TaskCreateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskResponseDto;
import com.example.TaskManagementSystem.task.dto.TaskUpdateRequestDto;

public interface TaskService {
    void create(TaskCreateRequestDto dto);
    void update(TaskUpdateRequestDto dto);
    void delete(Long id);
    TaskResponseDto get(Long id);
}
