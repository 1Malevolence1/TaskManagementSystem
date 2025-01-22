package com.example.TaskManagementSystem.comment.service;

import com.example.TaskManagementSystem.comment.dto.CommentCreateRequestDto;
import com.example.TaskManagementSystem.comment.dto.CommentResponseDto;
import com.example.TaskManagementSystem.comment.mapper.CommentMapperManager;
import com.example.TaskManagementSystem.comment.repository.CommentRepository;
import com.example.TaskManagementSystem.task.serivce.TaskValidate;
import com.example.TaskManagementSystem.utils.exception.Error;
import com.example.TaskManagementSystem.utils.exception.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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
    public void create(CommentCreateRequestDto dto) {
        commentValidate.validate(dto.taskId(), dto.accountId());
         try {
             repository.save(
                     mapper.toModel(
                             dto
                     )
             );
         } catch (DataAccessException e){
             throw new PersistenceException(new Error(e.getMessage()), e);
         }
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<CommentResponseDto> getAllByTaskId(Long taskId) {
        taskValidate.validateTaskExists(taskId);
        return mapper.toDto(repository.findAllByTaskId(taskId));
    }
}
