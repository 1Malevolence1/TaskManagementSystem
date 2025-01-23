package com.example.TaskManagementSystem.comment.service;


import com.example.TaskManagementSystem.account.utils.AccountValidate;
import com.example.TaskManagementSystem.security.jwt.JwtService;
import com.example.TaskManagementSystem.task.serivce.TaskOwnershipValidator;
import com.example.TaskManagementSystem.task.serivce.TaskValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentValidate {

    private final TaskValidate taskValidate;
    private final JwtService jwtService;
    private final AccountValidate accountValidate;
    private final CommentAccountValidator commentAccountValidator;
    private final TaskOwnershipValidator taskOwnershipValidator;

    public Long validateForCreate(Long taskId, String token) {
        taskValidate.validateTaskExists(taskId);
        Long accountId = getAccountId(token);
        accountValidate.validateAccountExists(accountId);
        taskOwnershipValidator.validateTaskOwnership(taskId, accountId,
                jwtService.extractRole(token)
        );
        return accountId;
    }


    public void validate(Long commentId, String token) {
        commentAccountValidator.validate(commentId, getAccountId(token));
    }

    private Long getAccountId(String token) {
        return jwtService.extractUserId(token);
    }
}
