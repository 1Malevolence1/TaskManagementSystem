package com.example.TaskManagementSystem.comment.service;


import com.example.TaskManagementSystem.comment.exception.CommentNotHaveAccount;
import com.example.TaskManagementSystem.security.jwt.JwtService;
import com.example.TaskManagementSystem.utils.exception.Error;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CommentAccountValidator {


    private final CommentAccountService commentAccountService;

    public void validate(Long commentId, Long accountId){
        Long findAccountId = commentAccountService.getAccountIdByCommentId(commentId);
        if(!Objects.equals(accountId, findAccountId)) throw new CommentNotHaveAccount(new Error("Комментарий с ID::%d не принадлежит аккаунту с ID::%d".formatted(commentId, accountId)));
    }
}
