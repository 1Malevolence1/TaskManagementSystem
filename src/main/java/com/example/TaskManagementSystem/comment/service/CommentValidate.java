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

    public void  validate(Long taskId, Long accountId, String token){
            taskValidate.validateTaskExists(taskId);
            accountValidate.validateAccountExists(accountId);
            taskOwnershipValidator.validateTaskOwnership(taskId, accountId,
                    jwtService.extractRole(token)
            );
    }


    public void  validate(Long commentId , String token){
            Long accountId = jwtService.extractUserId(token);
            commentAccountValidator.validate(commentId, accountId);
    }
}
