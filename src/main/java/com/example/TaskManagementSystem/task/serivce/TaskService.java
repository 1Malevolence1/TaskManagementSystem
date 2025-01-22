package com.example.TaskManagementSystem.task.serivce;

import com.example.TaskManagementSystem.task.dto.*;

public interface TaskService {
    void create(TaskCreateRequestDto dto);
    void update(TaskAdminUpdateRequestDto dto,  Long authorId);
    void update(TaskUserUpdateRequestDto dto, Long assigneeId);
    TaskIdsDto getIdsAccount(Long taskId);
    void delete(Long id);
    TaskResponseDto get(Long id);
    boolean exist(Long taskId);
}
