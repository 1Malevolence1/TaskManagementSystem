package com.example.TaskManagementSystem.task.serivce;

import com.example.TaskManagementSystem.task.dto.TaskAdminUpdateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskCreateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskResponseDto;
import com.example.TaskManagementSystem.task.dto.TaskUserUpdateRequestDto;

public interface TaskService {
    void create(TaskCreateRequestDto dto);
    void update(TaskAdminUpdateRequestDto dto,  Long authorId);
    void update(TaskUserUpdateRequestDto dto);
    void delete(Long id);
    TaskResponseDto get(Long id);
}
