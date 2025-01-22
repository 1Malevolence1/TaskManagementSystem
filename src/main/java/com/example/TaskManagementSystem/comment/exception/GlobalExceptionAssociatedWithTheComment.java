package com.example.TaskManagementSystem.comment.exception;


import com.example.TaskManagementSystem.task.exception.AssigneeDoesNotBelongTask;
import com.example.TaskManagementSystem.task.exception.AuthorDoesNotBelongTask;
import com.example.TaskManagementSystem.utils.exception.Error;
import com.example.TaskManagementSystem.utils.exception.ErrorProblemDetails;
import com.example.TaskManagementSystem.utils.exception.ProblemDetailsErrors;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionAssociatedWithTheComment {



    @ExceptionHandler(BindException.class)
    public ResponseEntity<ProblemDetailsErrors> handlerBindException(BindException e){
        List<ErrorProblemDetails> errors = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ErrorProblemDetails(fieldError.getDefaultMessage(), fieldError.getField())).toList();
        return ResponseEntity.badRequest().body(new ProblemDetailsErrors(errors));
    }


    @ExceptionHandler(AssigneeDoesNotBelongTask.class)
    public ResponseEntity<Error> handlerAssigneeDoesNotBelongTask(AssigneeDoesNotBelongTask e){
        return ResponseEntity.badRequest().body(e.getError());
    }


    @ExceptionHandler(AuthorDoesNotBelongTask.class)
    public ResponseEntity<Error> handlerAuthorDoesNotBelongTask(AuthorDoesNotBelongTask e){
        return ResponseEntity.badRequest().body(e.getError());
    }
}
