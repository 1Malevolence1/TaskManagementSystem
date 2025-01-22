package com.example.TaskManagementSystem.task.serivce;


import com.example.TaskManagementSystem.task.dto.TaskAdminUpdateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskCreateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskUserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskValidate {


    private final TaskSelfAssignmentValidator taskSelfAssignmentValidator;
    private final TaskExistValidate taskExistValidate;
    private final TaskEnumTypeValidate taskEnumTypeValidate;
    private final TaskBelongAssignValidate taskBelongAssignValidate;


    public void validate(TaskCreateRequestDto dto) {
        if (dto.assigneeId() != null) {
            validateSelfAssignment(dto.authorId(), dto.assigneeId());
        }
        validateStatusType(dto.status());
        validatePriorityType(dto.priority());
    }

    public void validate(TaskUserUpdateRequestDto dto, Long assigneeId) {
        validateTaskExists(dto.id());
        validateTaskBelongAssignee(assigneeId, dto.id());
        validateStatusType(dto.status());
    }

    public void validate(TaskAdminUpdateRequestDto dto, Long authorId) {
        validateTaskExists(dto.id());
        validateSelfAssignment(authorId, dto.assigneeId());
        validateStatusType(dto.status());
        validatePriorityType(dto.priority());
    }


    private void validateSelfAssignment(Long authorId, Long assigneeId) {
        taskSelfAssignmentValidator.validateSelfAssignment(authorId, assigneeId);
    }


    public void validateTaskExists(Long taskId) {
        taskExistValidate.checkTaskExistence(taskId);
    }

    private void validateStatusType(String status) {
        taskEnumTypeValidate.validateStatus(status);
    }

    private void validatePriorityType(String priority) {
        taskEnumTypeValidate.validatePriority(priority);
    }

    private void validateTaskBelongAssignee(Long assignId, Long taskId) {
        taskBelongAssignValidate.validateBelong(assignId, taskId);
    }
}
