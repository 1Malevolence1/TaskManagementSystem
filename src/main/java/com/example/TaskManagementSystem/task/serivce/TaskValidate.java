package com.example.TaskManagementSystem.task.serivce;


import com.example.TaskManagementSystem.account.utils.AccountValidate;
import com.example.TaskManagementSystem.task.dto.TaskAdminUpdateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskCreateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskUserUpdateRequestDto;
import com.example.TaskManagementSystem.task.model.Priority;
import com.example.TaskManagementSystem.task.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskValidate {


    private final TaskSelfAssignmentValidator taskSelfAssignmentValidator;
    private final TaskExistValidate taskExistValidate;
    private final TaskEnumTypeValidate taskEnumTypeValidate;
    private final TaskBelongAccountValidate taskBelongAccountValidate;
    private final AccountValidate accountValidate;


    public void validate(TaskCreateRequestDto dto, Long authorId) {
        if (dto.assigneeId() != null) {
            validateSelfAssignment(authorId, dto.assigneeId());
        }

        if(dto.assigneeId() != null) {
            accountValidate.validateAccountExists(dto.assigneeId());
        }

        accountValidate.validateAccountExists(authorId);

        validateStatusType(dto.status());
        validatePriorityType(dto.priority());
    }

    public void validate(TaskUserUpdateRequestDto dto, Long assigneeId) {
        validateTaskExists(dto.id());
        accountValidate.validateAccountExists(assigneeId);
        validateTaskBelongAssignee(assigneeId, dto.id());
        validateStatusType(dto.status());
    }

    public void validate(TaskAdminUpdateRequestDto dto, Long authorId) {
        if (dto.assigneeId() != null) {
            validateSelfAssignment(authorId, dto.assigneeId());
        }

        if(dto.assigneeId() != null) {
            accountValidate.validateAccountExists(dto.assigneeId());
        }

        accountValidate.validateAccountExists(authorId);
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

    private void validateStatusType(Status status) {
        taskEnumTypeValidate.validateStatus(status);
    }

    private void validatePriorityType(Priority priority) {
        taskEnumTypeValidate.validatePriority(priority);
    }

    public void validateTaskBelongAssignee(Long assignId, Long taskId) {
        taskBelongAccountValidate.validateBelongAssignee(assignId, taskId);
    }

    public void validateTaskBelongAuthor(Long authorId, Long taskId) {
        taskBelongAccountValidate.validateBelongAssignee(authorId, taskId);
    }


}
