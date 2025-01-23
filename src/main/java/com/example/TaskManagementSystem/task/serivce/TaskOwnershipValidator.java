package com.example.TaskManagementSystem.task.serivce;


import com.example.TaskManagementSystem.security.jwt.JwtService;
import com.example.TaskManagementSystem.task.dto.TaskIdsDto;
import com.example.TaskManagementSystem.task.exception.AssigneeDoesNotBelongTask;
import com.example.TaskManagementSystem.task.exception.AuthorDoesNotBelongTask;
import com.example.TaskManagementSystem.utils.exception.Error;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskOwnershipValidator {

    private final TaskService taskService;
    public void validateTaskOwnership(Long taskId, Long accountId, String role) {
        TaskIdsDto ids = taskService.getIdsAccount(taskId);
        if (role.equals("ROLE_USER")) {
            if (!Objects.equals(ids.assigneeId(), accountId)) {
                throw new AssigneeDoesNotBelongTask(new Error(
                        "Задача с ID::%d не принадлежит исполнителю с ID::%d".formatted(taskId, accountId)
                ));
            }
        }
    }
}
