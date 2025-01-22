package com.example.TaskManagementSystem.comment.repository;

import com.example.TaskManagementSystem.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //   @Query(value = "select c.* from comment as c where c.task_id = :taskId ", nativeQuery = true)
//    List<Comment> findAllByTaskId(@Param("taskId)Long taskId);


    @Query(value = "select c from  Comment c where c.task.id = :taskId")
    List<Comment> findAllByTaskId(Long taskId);

    @Query("select c.account.id from Comment c where c.account.id = ?1")
    Optional<Long> findByAccount_Id(Long commentId);
}
