package com.example.TaskManagementSystem.task.exceptin;


import com.example.TaskManagementSystem.utils.exception.Error;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SelfAssignmentException extends RuntimeException {
    private final Error error;
}
