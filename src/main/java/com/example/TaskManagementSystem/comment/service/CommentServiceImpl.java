package com.example.TaskManagementSystem.comment.service;

import com.example.TaskManagementSystem.comment.dto.CommentCreateRequestDto;
import com.example.TaskManagementSystem.comment.dto.CommentResponseDto;
import com.example.TaskManagementSystem.comment.mapper.CommentMapperManager;
import com.example.TaskManagementSystem.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final CommentMapperManager mapper;

    @Override
    public void create(CommentCreateRequestDto dto) {
            repository.save(
                    mapper.toModel(
                            dto
                    )
            );
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<CommentResponseDto> getAllByTaskId(Long taskId) {
        return mapper.toDto(repository.findAllByTaskId(taskId));
    }
}
