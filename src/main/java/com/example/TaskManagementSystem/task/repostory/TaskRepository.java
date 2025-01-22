package com.example.TaskManagementSystem.task.repostory;

import com.example.TaskManagementSystem.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select (count(t) > 0) from Task t where t.assignee.id = ?1 and t.id = ?2")
    boolean existsByAssignee_IdAndId(Long assignee, Long task);
}
