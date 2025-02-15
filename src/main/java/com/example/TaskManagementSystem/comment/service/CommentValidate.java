package com.example.TaskManagementSystem.comment.service;


import com.example.TaskManagementSystem.account.utils.AccountValidate;
import com.example.TaskManagementSystem.task.serivce.TaskOwnershipValidator;
import com.example.TaskManagementSystem.task.serivce.TaskValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentValidate {

    private final TaskValidate taskValidate;

    private final AccountValidate accountValidate;
    private final CommentAccountValidator commentAccountValidator;
    private final TaskOwnershipValidator taskOwnershipValidator;

    public void validateForCreate(Long taskId, Long accountId, String role) {
        taskValidate.validateTaskExists(taskId);
        accountValidate.validateAccountExists(accountId);
        taskOwnershipValidator.validateTaskOwnership(taskId, accountId, role);
    }


    public void validate(Long commentId, Long accountId) {
        commentAccountValidator.validate(commentId, accountId);
    }
}
