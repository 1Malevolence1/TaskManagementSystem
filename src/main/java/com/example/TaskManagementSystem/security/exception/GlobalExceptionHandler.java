package com.example.TaskManagementSystem.security.exception;



import com.example.TaskManagementSystem.utils.exception.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<Error> handleExpiredTokenException(ExpiredTokenException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getError());
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Error> handleInvalidTokenException(InvalidTokenException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getError());
    }

    @ExceptionHandler(AccountAlreadyRegistered.class)
    public ResponseEntity<Error> handleAccountAlreadyRegistered(AccountAlreadyRegistered ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getError());
    }

    @ExceptionHandler(BadRequestSingInCustomer.class)
    public ResponseEntity<Error> handleBadRequestSingInCustomer(BadRequestSingInCustomer ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getError());
    }
}