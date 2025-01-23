package com.example.TaskManagementSystem.comment.service;


import com.example.TaskManagementSystem.comment.exception.CommentNotBelongAccount;
import com.example.TaskManagementSystem.utils.exception.Error;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommentAccountValidator {


    private final CommentAccountService commentAccountService;

    public void validate(Long commentId, Long accountId) {
        Long findAccountId = commentAccountService.getAccountIdByCommentId(commentId);
        if(!Objects.equals(accountId, findAccountId)) throw new CommentNotBelongAccount(new Error("Комментарий с ID::%d не принадлежит аккаунту с ID::%d".formatted(commentId, accountId)));
    }
}
