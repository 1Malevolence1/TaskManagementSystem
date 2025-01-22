package com.example.TaskManagementSystem.task.serivce;

import com.example.TaskManagementSystem.task.dto.*;
import com.example.TaskManagementSystem.utils.exception.Error;
import com.example.TaskManagementSystem.utils.exception.PersistenceException;
import com.example.TaskManagementSystem.task.mapper.TaskMapperManager;
import com.example.TaskManagementSystem.task.model.Task;
import com.example.TaskManagementSystem.task.repostory.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
@Slf4j

public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final TaskMapperManager mapper;
    private final TaskValidate taskValidate;


    @Override
    @Transactional
    public void create(TaskCreateRequestDto dto) {
        taskValidate.validate(dto);
        try {
            repository.save(mapper.toModel(dto));
        } catch (DataAccessException e) {
            throw new PersistenceException(new Error("Ошибка при сохранении задачи в базу данных"), e);
        }
    }

    @Override
    @Transactional
    public void update(TaskAdminUpdateRequestDto dto, Long authorId) {
        taskValidate.validate(dto, authorId);
        Task mapperTask = mapper.toModel(dto);
        try {
            repository.findById(dto.id()).ifPresentOrElse(
                    task -> {
                        if (dto.title() != null) task.setTitle(mapperTask.getTitle());
                        if (dto.description() != null) task.setDescription(mapperTask.getDescription());
                        if (dto.status() != null) task.setStatus(mapperTask.getStatus());
                        if (dto.priority() != null) task.setPriority(mapperTask.getPriority());
                    }, () -> {
                        throw new NoSuchElementException("Not found task by ID::%d".formatted(dto.id()));
                    }
            );
        } catch (DataAccessException e) {
            throw new PersistenceException(new Error("Ошибка при сохранении задачи в базу данных"), e);
        }
    }

    @Override
    @Transactional
    public void update(TaskUserUpdateRequestDto dto, Long assigneeId) {
        taskValidate.validate(dto, assigneeId);
        Task mapperTask = mapper.toModel(dto);
        try {
            repository.findById(dto.id()).ifPresentOrElse(
                    task -> {
                        task.setStatus(mapperTask.getStatus());
                    }, () -> {
                        throw new NoSuchElementException("Not found task by ID::%d".formatted(dto.id()));
                    }
            );
        } catch (DataAccessException e) {
            throw new PersistenceException(new Error("Ошибка при сохранении задачи в базу данных"), e);
        }
    }

    @Override
    public TaskIdsDto getIdsAccount(Long taskId) {
        return repository.findIdsAuthorAndAssigneeByTaskId(taskId);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public TaskResponseDto get(Long id) {
        Task task = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found task by ID::%d".formatted(id)));
        log.info("{}", task.toString());
        return mapper.toDto(task);

    }

    @Override
    public boolean exist(Long taskId) {
        return repository.existsById(taskId);
    }
}
