package com.example.TaskManagementSystem.comment.service;

import com.example.TaskManagementSystem.account.model.Account;
import com.example.TaskManagementSystem.comment.dto.CommentCreateRequestDto;
import com.example.TaskManagementSystem.comment.dto.CommentResponseDto;
import com.example.TaskManagementSystem.comment.mapper.CommentMapperManager;
import com.example.TaskManagementSystem.comment.model.Comment;
import com.example.TaskManagementSystem.comment.repository.CommentRepository;
import com.example.TaskManagementSystem.task.serivce.TaskValidate;
import com.example.TaskManagementSystem.utils.exception.Error;
import com.example.TaskManagementSystem.utils.exception.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.tokens.CommentToken;

import java.util.List;



@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final CommentMapperManager mapper;
    private final TaskValidate taskValidate;
    private final CommentValidate commentValidate;

    @Override
    public void create(CommentCreateRequestDto dto, Long accountId, String role) {
      commentValidate.validateForCreate(dto.taskId(),accountId,role);
         try {
             Comment comment = mapper.toModel(dto);
             comment.setAccount(Account.builder().id(accountId).build());
             repository.save(comment);
         } catch (DataAccessException e){
             throw new PersistenceException(new Error(e.getMessage()));
         }
    }

    @Override
    public void delete(Long id, Long accountId) {
        commentValidate.validate(id, accountId);
        repository.deleteById(id);
    }

    @Override
    public List<CommentResponseDto> getAllByTaskId(Long taskId) {
        taskValidate.validateTaskExists(taskId);
        return mapper.toDto(repository.findAllByTaskId(taskId));
    }
}
