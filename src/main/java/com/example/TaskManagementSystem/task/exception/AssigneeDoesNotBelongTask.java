package com.example.TaskManagementSystem.task.exception;


import com.example.TaskManagementSystem.utils.exception.Error;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AssigneeDoesNotBelongTask extends RuntimeException {
     private final Error error;
}
