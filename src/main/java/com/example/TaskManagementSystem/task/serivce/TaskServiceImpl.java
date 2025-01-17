package com.example.TaskManagementSystem.task.serivce;

import com.example.TaskManagementSystem.task.dto.TaskCreateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskResponseDto;
import com.example.TaskManagementSystem.task.dto.TaskUpdateRequestDto;
import com.example.TaskManagementSystem.task.mapper.TaskMapperManager;
import com.example.TaskManagementSystem.task.repostory.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final TaskMapperManager mapper;

    //%TODO сделать обработчики на случай ошибок на уровен SQL
    @Override
    public void create(TaskCreateRequestDto dto) {
        repository.save(
                mapper.toModel(dto)
        );
    }

    //%TODO сделать обработчики на случай ошибок на уровен SQL
    @Override
    public void update(TaskUpdateRequestDto dto) {
        repository.findById(dto.id()).ifPresentOrElse(
                task -> {
                    if (dto.title() != null) task.setTitle(dto.title());
                    if (dto.description() != null) task.setDescription(dto.description());
                    if (dto.status() != null) task.setStatus(dto.status());
                    if (dto.priority() != null) task.setPriority(dto.priority());
                }, () -> {
                    throw new NoSuchElementException("Not found task by ID::%d".formatted(dto.id()));
                }
        );
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public TaskResponseDto get(Long id) {
        return mapper.toDto(
                repository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found task by ID::%d".formatted(id)))
        );
    }
}
