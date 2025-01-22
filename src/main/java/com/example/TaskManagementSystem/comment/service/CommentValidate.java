package com.example.TaskManagementSystem.comment.service;


import com.example.TaskManagementSystem.account.AccountValidate;
import com.example.TaskManagementSystem.task.serivce.TaskValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentValidate {

    private final TaskValidate taskValidate;
    private final AccountValidate accountValidate;

    public void  validate(Long taskId, Long accountId){
            taskValidate.validateTaskExists(taskId);
            accountValidate.validateAccountExists(accountId);
    }
}
