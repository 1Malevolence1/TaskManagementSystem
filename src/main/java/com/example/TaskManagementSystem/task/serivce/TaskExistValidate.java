package com.example.TaskManagementSystem.task.serivce;


import com.example.TaskManagementSystem.task.repostory.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class TaskExistValidate {

    private final TaskRepository taskRepository;

    public void checkTaskExistence(Long taskId){
        if(taskRepository.existsById(taskId)) throw  new NoSuchElementException("Not found task with ID::%d".formatted(taskId));
    }
}
