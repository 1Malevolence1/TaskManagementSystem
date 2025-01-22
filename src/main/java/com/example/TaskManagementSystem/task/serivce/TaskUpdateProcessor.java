package com.example.TaskManagementSystem.task.serivce;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskUpdateProcessor {

    private final TaskService taskService;

}
