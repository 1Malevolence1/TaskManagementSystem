package com.example.TaskManagementSystem.comment.service;


import com.example.TaskManagementSystem.account.AccountValidate;
import com.example.TaskManagementSystem.security.jwt.JwtService;
import com.example.TaskManagementSystem.task.dto.TaskIdsDto;
import com.example.TaskManagementSystem.task.exception.AssigneeDoesNotBelongTask;
import com.example.TaskManagementSystem.task.exception.AuthorDoesNotBelongTask;
import com.example.TaskManagementSystem.task.serivce.TaskOwnershipValidator;
import com.example.TaskManagementSystem.task.serivce.TaskService;
import com.example.TaskManagementSystem.task.serivce.TaskValidate;
import com.example.TaskManagementSystem.utils.exception.Error;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

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
