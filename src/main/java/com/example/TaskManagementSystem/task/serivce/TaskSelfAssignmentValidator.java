package com.example.TaskManagementSystem.task.serivce;


import com.example.TaskManagementSystem.utils.exception.Error;
import com.example.TaskManagementSystem.task.exceptin.SelfAssignmentException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TaskSelfAssignmentValidator {

    public void validateSelfAssignment(Long authorId,Long assigneeId){
        if(Objects.equals(authorId, assigneeId)) throw new SelfAssignmentException(new Error("Автор задачи не может назначить себя исполнителем. Author ID: %d".formatted(authorId)));
    }
}

