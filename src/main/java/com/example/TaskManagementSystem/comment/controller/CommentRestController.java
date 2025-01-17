package com.example.TaskManagementSystem.comment.controller;


import com.example.TaskManagementSystem.comment.dto.CommentCreateRequestDto;
import com.example.TaskManagementSystem.comment.dto.CommentResponseDto;
import com.example.TaskManagementSystem.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/task/{taskId:\\d+}/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentRestController {

    private final CommentService commentService;

    @PostMapping("")
    public ResponseEntity<Void> createComment(@RequestBody CommentCreateRequestDto dto){
        log.info("start method by create Comment. Dto: {}", dto);
        commentService.create(dto);
        log.info("comment create");
        log.info("methode complete");
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{commentId:\\d+}")
    public ResponseEntity<Void> deleteComment(@PathVariable(name = "commentId") Long id){
        log.info("start method by delete Comment. ID: {}", id);
        commentService.delete(id);
        log.info("comment delete");
        log.info("methode complete");
        return ResponseEntity.noContent().build();
    }


    // Добавить проверку на то, что задача есть
    @GetMapping("")
    public ResponseEntity<List<CommentResponseDto>> getCommentsForTask(@PathVariable(name = "taskId") Long id){
        log.info("start method getAllCommentByTaskId. taskID: {}", id);
       List<CommentResponseDto> dto =  commentService.getAllByTaskId(id);
        log.info("methode complete");
        return ResponseEntity.ok(dto);
    }

}
