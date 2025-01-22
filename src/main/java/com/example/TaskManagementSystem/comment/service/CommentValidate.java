package com.example.TaskManagementSystem.comment.service;


import com.example.TaskManagementSystem.account.AccountValidate;
import com.example.TaskManagementSystem.task.dto.TaskIdsDto;
import com.example.TaskManagementSystem.task.exception.AssigneeDoesNotBelongTask;
import com.example.TaskManagementSystem.task.exception.AuthorDoesNotBelongTask;
import com.example.TaskManagementSystem.task.serivce.TaskOwnershipValidator;
import com.example.TaskManagementSystem.task.serivce.TaskService;
import com.example.TaskManagementSystem.task.serivce.TaskValidate;
import com.example.TaskManagementSystem.utils.exception.Error;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CommentValidate {

    private final TaskValidate taskValidate;

    private final AccountValidate accountValidate;
    private final TaskOwnershipValidator taskOwnershipValidator;

    public void  validate(Long taskId, Long accountId, String role){
            taskValidate.validateTaskExists(taskId);
            accountValidate.validateAccountExists(accountId);
            taskOwnershipValidator.validateTaskOwnership(taskId, accountId, role);
    }
}
