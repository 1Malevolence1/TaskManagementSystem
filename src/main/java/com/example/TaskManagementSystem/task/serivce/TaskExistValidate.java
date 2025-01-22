package com.example.TaskManagementSystem.task.serivce;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class TaskExistValidate {

    private final TaskService taskService;

    public void checkTaskExistence(Long taskId){
        if(!taskService.exist(taskId)) throw  new NoSuchElementException("Not found task with ID::%d".formatted(taskId));
    }
}
