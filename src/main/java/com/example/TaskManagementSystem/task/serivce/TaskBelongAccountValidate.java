package com.example.TaskManagementSystem.task.serivce;


import com.example.TaskManagementSystem.task.exception.AssigneeDoesNotBelongTask;
import com.example.TaskManagementSystem.task.exception.AuthorDoesNotBelongTask;
import com.example.TaskManagementSystem.task.repostory.TaskRepository;
import com.example.TaskManagementSystem.utils.exception.Error;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskBelongAccountValidate {

    private final TaskRepository taskRepository;

    public void validateBelongAssignee(Long assigneeId, Long taskId){
         if(!taskRepository.existsByAssignee_IdAndId(assigneeId, taskId)) throw new AssigneeDoesNotBelongTask(new Error("Задача с ID:: %d не принадлежит исполнителю с ID:: %d".formatted(taskId, assigneeId)));
    }

    public void validateBelongAuthor(Long authorId, Long taskId){
        if(!taskRepository.existsByAuthor_IdAndId(authorId, taskId)) throw new AuthorDoesNotBelongTask(new Error("Задача с ID:: %d не принадлежит администратору с ID:: %d".formatted(taskId, authorId)));
    }
}
