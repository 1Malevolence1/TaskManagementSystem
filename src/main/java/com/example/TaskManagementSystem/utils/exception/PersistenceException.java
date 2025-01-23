package com.example.TaskManagementSystem.utils.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PersistenceException extends RuntimeException {
    private final Error error;

}
