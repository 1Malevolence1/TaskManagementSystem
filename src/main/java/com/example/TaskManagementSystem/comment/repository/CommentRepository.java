package com.example.TaskManagementSystem.comment.repository;

import com.example.TaskManagementSystem.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
