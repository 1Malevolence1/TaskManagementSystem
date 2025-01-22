package com.example.TaskManagementSystem.task.serivce;


import com.example.TaskManagementSystem.task.exception.AssigneeDoesNotBelongTask;
import com.example.TaskManagementSystem.task.repostory.TaskRepository;
import com.example.TaskManagementSystem.utils.exception.Error;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskBelongAssignValidate {

    private final TaskRepository taskRepository;

    public void validateBelong(Long assigneeId, Long taskId){
         if(!taskRepository.existsByAssignee_IdAndId(assigneeId, taskId)) throw new AssigneeDoesNotBelongTask(new Error("Задача с ID:: %d не принадлежит исполнителю с ID:: %d".formatted(taskId, assigneeId)));
    }
}
