package com.example.TaskManagementSystem.comment.service;

import com.example.TaskManagementSystem.account.model.Account;
import com.example.TaskManagementSystem.comment.dto.CommentResponseDto;
import com.example.TaskManagementSystem.comment.mapper.CommentMapperManager;
import com.example.TaskManagementSystem.comment.model.Comment;
import com.example.TaskManagementSystem.comment.repository.CommentRepository;
import com.example.TaskManagementSystem.task.model.Task;
import com.example.TaskManagementSystem.task.serivce.TaskValidate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {


    @Mock
    CommentRepository repository;

    @Mock
    CommentMapperManager mapper;

    @Mock
     TaskValidate taskValidate;

    @Mock
    CommentValidate commentValidate;

    @InjectMocks
    CommentServiceImpl commentService;

    @Test
    void delete_shouldDeleteComment() {
        Long commentId = 1L;
        Long accountId = 1L;

        doNothing().when(commentValidate).validate(commentId, accountId);
        doNothing().when(repository).deleteById(commentId);

        commentService.delete(commentId, accountId);

        verify(commentValidate, times(1)).validate(commentId, accountId);
        verify(repository, times(1)).deleteById(commentId);
    }

    @Test
    void getAllByTaskId_shouldReturnComments() {
        Long taskId = 1L;

        Comment comment1 = new Comment();
        comment1.setId(1L);
        comment1.setText("Первый комментарий");
        comment1.setTask(Task.builder().id(taskId).build());
        comment1.setAccount(Account.builder().id(1L).build());

        Comment comment2 = new Comment();
        comment2.setId(2L);
        comment2.setText("Второй комментарий");
        comment1.setTask(Task.builder().id(taskId).build());
        comment1.setAccount(Account.builder().id(2L).build());

        List<Comment> comments = List.of(comment1, comment2);

        CommentResponseDto commentResponseDto1 = new CommentResponseDto(1L, "Первый комментарий", taskId, 1L);
        CommentResponseDto commentResponseDto2 = new CommentResponseDto(2L, "Второй комментарий", taskId, 2L);

        List<CommentResponseDto> expectedResponse = List.of(commentResponseDto1, commentResponseDto2);

        when(repository.findAllByTaskId(taskId)).thenReturn(comments);
        when(mapper.toDto(comments)).thenReturn(expectedResponse);
        doNothing().when(taskValidate).validateTaskExists(taskId);

        List<CommentResponseDto> result = commentService.getAllByTaskId(taskId);

        assertEquals(expectedResponse, result);
        verify(taskValidate, times(1)).validateTaskExists(taskId);
        verify(repository, times(1)).findAllByTaskId(taskId);
        verify(mapper, times(1)).toDto(comments);
    }
}

