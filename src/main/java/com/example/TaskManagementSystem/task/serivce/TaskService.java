package com.example.TaskManagementSystem.task.serivce;

import com.example.TaskManagementSystem.task.dto.*;
import com.example.TaskManagementSystem.task.model.Priority;
import com.example.TaskManagementSystem.task.model.Status;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {
    void create(TaskCreateRequestDto dto);
    void update(TaskAdminUpdateRequestDto dto,  Long authorId);
    void update(TaskUserUpdateRequestDto dto, Long assigneeId);
    TaskIdsDto getIdsAccount(Long taskId);
    void delete(Long id);
    TaskResponseDto get(Long id);
    boolean exist(Long taskId);

    Page<TaskResponseDto> getAllTasks(Long accountId, Status status, Priority priority, int size, int page);
}
