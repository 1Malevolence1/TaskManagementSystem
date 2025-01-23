package com.example.TaskManagementSystem.account.exception;


import com.example.TaskManagementSystem.security.exception.AccountAlreadyRegistered;
import com.example.TaskManagementSystem.utils.exception.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAssociatedWithTheAccount {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Error> handlerIllegalArgumentException(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(new Error(e.getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Error> handlerIllegalStateException(IllegalStateException e){
        return ResponseEntity.badRequest().body(new Error(e.getMessage()));
    }

    @ExceptionHandler(AccountAlreadyRegistered.class)
    public ResponseEntity<Error> AccountAlreadyRegistered(AccountAlreadyRegistered e){
        return ResponseEntity.badRequest().body(e.getError());
    }
}
