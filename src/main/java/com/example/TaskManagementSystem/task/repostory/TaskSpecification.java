package com.example.TaskManagementSystem.task.repostory;

import com.example.TaskManagementSystem.task.model.Priority;
import com.example.TaskManagementSystem.task.model.Status;
import com.example.TaskManagementSystem.task.model.Task;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;


@Component
public class TaskSpecification {

    public Specification<Task> hasAccount(Long accountId) {
        return (root, query, criteriaBuilder) -> {
            if (accountId == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            return criteriaBuilder.or(
                    criteriaBuilder.equal(root.get("author").get("id"), accountId),
                    criteriaBuilder.equal(root.get("assignee").get("id"), accountId)
            );
        };
    }

    public Specification<Task> hasStatus(Status status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            return criteriaBuilder.equal(root.get("status"), status);
        };
    }

    public Specification<Task> hasPriority(Priority priority) {
        return (root, query, criteriaBuilder) -> {
            if (priority == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            return criteriaBuilder.equal(root.get("priority"), priority);
        };
    }
}

