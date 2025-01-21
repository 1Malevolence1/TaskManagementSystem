package com.example.TaskManagementSystem.task.serivce;


import com.example.TaskManagementSystem.task.dto.TaskCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskValidate {


    private final TaskSelfAssignmentValidator taskSelfAssignmentValidator;

    public void validate(TaskCreateRequestDto dto){
        taskSelfAssignmentValidator.validateSelfAssignment(dto.authorId(), dto.assigneeId());
    }


}
