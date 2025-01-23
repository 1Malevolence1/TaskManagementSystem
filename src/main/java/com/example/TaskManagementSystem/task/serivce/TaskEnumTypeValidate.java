package com.example.TaskManagementSystem.task.serivce;


import com.example.TaskManagementSystem.task.exception.PriorityDoesNotHaveMatchingType;
import com.example.TaskManagementSystem.task.exception.StatusDoesNotHaveMatchingType;
import com.example.TaskManagementSystem.task.model.Priority;
import com.example.TaskManagementSystem.task.model.Status;
import com.example.TaskManagementSystem.utils.exception.Error;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TaskEnumTypeValidate {


    public void validateStatus(Status status) {
        if (status != null && !isValidStatus(status)) {
            throw new StatusDoesNotHaveMatchingType(
                    new Error("Недопустимый статус задачи: %s. Допустимые значения: %s"
                            .formatted(status, Arrays.toString(Status.values())))
            );
        }
    }
    public void validatePriority(Priority priority) {
        if (priority != null && !isValidPriority(priority)) {
            throw new PriorityDoesNotHaveMatchingType(
                    new Error("Недопустимый приоритет задачи: %s. Допустимые значения: %s"
                            .formatted(priority, Arrays.toString(Priority.values())))
            );
        }
    }
    private boolean isValidStatus(Status status) {
        return status == Status.COMPLETED || status == Status.IN_PROGRESS || status == Status.WAITING;
    }
    private boolean isValidPriority(Priority priority) {
        return priority == Priority.HIGH || priority == Priority.MEDIUM || priority == Priority.LOW;
    }
}
