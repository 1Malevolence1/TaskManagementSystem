package com.example.TaskManagementSystem.comment.exception;

import com.example.TaskManagementSystem.utils.exception.Error;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class CommentNotBelongAccount extends RuntimeException {
    private final Error error;
}
