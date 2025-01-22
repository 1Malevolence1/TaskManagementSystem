package com.example.TaskManagementSystem.task.serivce;


import com.example.TaskManagementSystem.task.exception.PriorityDoesNotHaveMatchingType;
import com.example.TaskManagementSystem.task.exception.StatusDoesNotHaveMatchingType;
import com.example.TaskManagementSystem.task.model.Priority;
import com.example.TaskManagementSystem.task.model.Status;
import com.example.TaskManagementSystem.utils.exception.Error;
import org.springframework.stereotype.Component;

@Component
public class TaskEnumTypeValidate {


    public void  validateStatus(String status){
        if(status != null) {
            if (!status.equals(Status.COMPLETED.name()) && !status.equals(Status.IN_PROGRESS.name()) && !status.equals(Status.WAITING.name()))
                throw new StatusDoesNotHaveMatchingType(new Error("Недопустимый статус задачи: %s. Допустимые значения: COMPLETED, IN_PROGRESS, WAITING".formatted(status)));
        }
    }

    public void  validatePriority(String priority){
        if(priority != null) {
            if (!priority.equals(Priority.HIGH.name()) && !priority.equals(Priority.MEDIUM.name()) && !priority.equals(Priority.LOW.name()))
                throw new PriorityDoesNotHaveMatchingType(new Error("Недопустимый статус приоритетата: %s. Допустимые значения: HIGH, MEDIUM, LOW".formatted(priority)));
        }
    }
}
