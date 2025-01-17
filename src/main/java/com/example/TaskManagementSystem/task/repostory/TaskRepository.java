package com.example.TaskManagementSystem.task.repostory;

import com.example.TaskManagementSystem.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
