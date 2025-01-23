package com.example.TaskManagementSystem.comment.service;


import com.example.TaskManagementSystem.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CommentAccountService {

    private final CommentRepository commentRepository;

    public Long getAccountIdByCommentId(Long commentId) {
        return commentRepository.findByAccount_Id(commentId).orElseThrow(() -> new NoSuchElementException("Комментария с ID::%d не существует".formatted(commentId)));
    }
}
