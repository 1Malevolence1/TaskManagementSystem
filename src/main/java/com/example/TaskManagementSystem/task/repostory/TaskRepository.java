package com.example.TaskManagementSystem.task.repostory;

import com.example.TaskManagementSystem.task.dto.TaskIdsDto;
import com.example.TaskManagementSystem.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select (count(t) > 0) from Task t where t.assignee.id = ?1 and t.id = ?2")
    boolean existsByAssignee_IdAndId(Long assignee, Long task);

    @Query("select (count(t) > 0) from Task t where t.assignee.id = ?1 and t.id = ?2")
    boolean existsByAuthor_IdAndId(Long author, Long task);

    @Query("select new com.example.TaskManagementSystem.task.dto.TaskIdsDto(t.author.id, t.assignee.id) from Task t where t.id = ?1")
    TaskIdsDto findIdsAuthorAndAssigneeByTaskId(Long taskId);
}
