package com.example.TaskManagementSystem.task.exception;

import com.example.TaskManagementSystem.utils.exception.Error;
import com.example.TaskManagementSystem.utils.exception.ErrorProblemDetails;
import com.example.TaskManagementSystem.utils.exception.PersistenceException;
import com.example.TaskManagementSystem.utils.exception.ProblemDetailsErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionAssociatedWithTheTask {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Error> handlerNoSuchElementException(NoSuchElementException e){
        return ResponseEntity.badRequest().body(new Error(e.getMessage()));
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<Error> handlerTaskPersistenceException(PersistenceException e){
        return ResponseEntity.badRequest().body(e.getError());
    }



    @ExceptionHandler(SelfAssignmentException.class)
    public ResponseEntity<Error> handlerSelfAssignmentException(SelfAssignmentException e){
        return ResponseEntity.badRequest().body(e.getError());
    }



    @ExceptionHandler(StatusDoesNotHaveMatchingType.class)
    public ResponseEntity<Error> handlerStatusDoesNotHaveMatchingType(StatusDoesNotHaveMatchingType e){
        return ResponseEntity.badRequest().body(e.getError());
    }

    @ExceptionHandler(PriorityDoesNotHaveMatchingType.class)
    public ResponseEntity<Error> handlerPriorityDoesNotHaveMatchingType( PriorityDoesNotHaveMatchingType e){
        return ResponseEntity.badRequest().body(e.getError());
    }



    @ExceptionHandler(AssigneeDoesNotBelongTask.class)
    public ResponseEntity<Error> handlerAssigneeDoesNotBelongTask(AssigneeDoesNotBelongTask e){
        return ResponseEntity.badRequest().body(e.getError());
    }



    @ExceptionHandler(BindException.class)
    public ResponseEntity<ProblemDetailsErrors> handlerBindException(BindException e){
        List<ErrorProblemDetails> errors = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ErrorProblemDetails(fieldError.getDefaultMessage(), fieldError.getField())).toList();
        return ResponseEntity.badRequest().body(new ProblemDetailsErrors(errors));
    }


}

