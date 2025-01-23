package com.example.TaskManagementSystem.task.serivce;

import com.example.TaskManagementSystem.account.dto.AccountResponseDto;
import com.example.TaskManagementSystem.account.model.Account;
import com.example.TaskManagementSystem.account.model.Role;
import com.example.TaskManagementSystem.task.dto.TaskAdminUpdateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskCreateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskResponseDto;
import com.example.TaskManagementSystem.task.mapper.TaskMapperManager;
import com.example.TaskManagementSystem.task.model.Priority;
import com.example.TaskManagementSystem.task.model.Status;
import com.example.TaskManagementSystem.task.model.Task;
import com.example.TaskManagementSystem.task.repostory.TaskRepository;
import com.example.TaskManagementSystem.task.repostory.TaskSpecification;
import com.example.TaskManagementSystem.utils.exception.PersistenceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapperManager taskMapperManager;

    @Mock
    private TaskValidate taskValidate;

    @Mock
    private TaskSpecification taskSpecification;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void findTaskById_shouldReturnTask() {
        Role authorRole = Role.builder()
                .id(1L)
                .name("ROLE_USER")
                .build();
        Role assigneeRole = Role.builder()
                .id(2L)
                .name("ROLE_ADMIN")
                .build();

        AccountResponseDto author = new AccountResponseDto(
                1L,
                "author@example.com",
                authorRole
        );
        AccountResponseDto assignee = new AccountResponseDto(
                2L,
                "assignee@example.com",
                assigneeRole
        );

        TaskResponseDto mockTaskResponse = new TaskResponseDto(
                1L,
                "Разработать дизайн главной страницы",
                "Создать современный и удобный дизайн для главной страницы сайта.",
                Status.COMPLETED,
                Priority.HIGH,
                List.of(),
                author,
                assignee
        );

        Task mockTask = new Task();
        mockTask.setId(1L);
        mockTask.setTitle("Разработать дизайн главной страницы");
        mockTask.setDescription("Создать современный и удобный дизайн для главной страницы сайта.");
        mockTask.setStatus(Status.COMPLETED);
        mockTask.setPriority(Priority.HIGH);
        mockTask.setAuthor(Account.builder().id(1L).role(authorRole).build());
        mockTask.setAssignee(Account.builder().id(2L).role(assigneeRole).build());

        when(taskRepository.findById(1L)).thenReturn(Optional.of(mockTask));
        when(taskMapperManager.toDto(mockTask)).thenReturn(mockTaskResponse);

        TaskResponseDto result = taskService.get(1L);

        assertNotNull(result);
        assertEquals("Разработать дизайн главной страницы", result.title());
        assertEquals("Создать современный и удобный дизайн для главной страницы сайта.", result.description());
        assertEquals(Status.COMPLETED, result.status());
        assertEquals(Priority.HIGH, result.priority());

        assertEquals(1L, result.author().id());
        assertEquals("author@example.com", result.author().email());
        assertEquals("ROLE_USER", result.author().role().getName());

        assertEquals(2L, result.assignee().id());
        assertEquals("assignee@example.com", result.assignee().email());
        assertEquals("ROLE_ADMIN", result.assignee().role().getName());
    }

    @Test
    void findTaskById_shouldThrowExceptionWhenTaskNotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> taskService.get(1L));
    }

    @Test
    void create_shouldSaveTask() {
        TaskCreateRequestDto dto = new TaskCreateRequestDto(
                "Разработать дизайн главной страницы",
                "Создать современный и удобный дизайн для главной страницы сайта.",
                Status.IN_PROGRESS,
                Priority.HIGH,
                2L
        );

        Long authorId = 1L;

        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());
        task.setPriority(dto.priority());
        task.setAssignee(Account.builder().id(dto.assigneeId()).build());
        task.setAuthor(Account.builder().id(authorId).build());

        when(taskMapperManager.toModel(dto)).thenReturn(task);
        doNothing().when(taskValidate).validate(dto, authorId);
        when(taskRepository.save(task)).thenReturn(task);

        taskService.create(dto, authorId);

        verify(taskValidate, times(1)).validate(dto, authorId);
        verify(taskMapperManager, times(1)).toModel(dto);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void create_shouldHandleDataAccessException() {
        TaskCreateRequestDto dto = new TaskCreateRequestDto(
                "Разработать дизайн главной страницы",
                "Создать современный и удобный дизайн для главной страницы сайта.",
                Status.IN_PROGRESS,
                Priority.HIGH,
                2L
        );

        Long authorId = 1L;

        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());
        task.setPriority(dto.priority());
        task.setAssignee(Account.builder().id(dto.assigneeId()).build());
        task.setAuthor(Account.builder().id(authorId).build());

        when(taskMapperManager.toModel(dto)).thenReturn(task);
        doNothing().when(taskValidate).validate(dto, authorId);
        when(taskRepository.save(task)).thenThrow(new DataAccessException("Ошибка базы данных") {
        });

        PersistenceException exception = assertThrows(PersistenceException.class, () -> {
            taskService.create(dto, authorId);
        });

        assertEquals("Ошибка при сохранении задачи в базу данных. Ошибка базы данных", exception.getError().getText());
    }

    @Test
    void create_shouldHandleNullAssignee() {
        TaskCreateRequestDto dto = new TaskCreateRequestDto(
                "Разработать дизайн главной страницы",
                "Создать современный и удобный дизайн для главной страницы сайта.",
                Status.IN_PROGRESS,
                Priority.HIGH,
                null
        );

        Long authorId = 1L;

        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());
        task.setPriority(dto.priority());
        task.setAuthor(Account.builder().id(authorId).build());

        when(taskMapperManager.toModel(dto)).thenReturn(task);
        doNothing().when(taskValidate).validate(dto, authorId);
        when(taskRepository.save(task)).thenReturn(task);

        taskService.create(dto, authorId);

        verify(taskValidate, times(1)).validate(dto, authorId);
        verify(taskMapperManager, times(1)).toModel(dto);
        verify(taskRepository, times(1)).save(task);
        assertNull(task.getAssignee());
    }

    @Test
    void update_shouldUpdateTask() {
        TaskAdminUpdateRequestDto dto = new TaskAdminUpdateRequestDto(
                1L,
                "Обновлённый заголовок",
                "Обновлённое описание",
                Status.IN_PROGRESS,
                Priority.MEDIUM,
                2L
        );

        Long authorId = 1L;

        Task existingTask = new Task();
        existingTask.setId(1L);
        existingTask.setTitle("Старый заголовок");
        existingTask.setDescription("Старое описание");
        existingTask.setStatus(Status.WAITING);
        existingTask.setPriority(Priority.LOW);
        existingTask.setAuthor(Account.builder().id(authorId).build());
        existingTask.setAssignee(Account.builder().id(3L).build());

        Task mapperTask = new Task();
        mapperTask.setTitle(dto.title());
        mapperTask.setDescription(dto.description());
        mapperTask.setStatus(dto.status());
        mapperTask.setPriority(dto.priority());
        mapperTask.setAssignee(Account.builder().id(dto.assigneeId()).build());

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(taskMapperManager.toModel(dto)).thenReturn(mapperTask);
        doNothing().when(taskValidate).validate(dto, authorId);

        taskService.update(dto, authorId);

        verify(taskValidate, times(1)).validate(dto, authorId);
        verify(taskMapperManager, times(1)).toModel(dto);
        verify(taskRepository, times(1)).findById(1L);

        assertEquals("Обновлённый заголовок", existingTask.getTitle());
        assertEquals("Обновлённое описание", existingTask.getDescription());
        assertEquals(Status.IN_PROGRESS, existingTask.getStatus());
        assertEquals(Priority.MEDIUM, existingTask.getPriority());
        assertEquals(2L, existingTask.getAssignee().getId());
    }

    @Test
    void update_shouldThrowExceptionWhenTaskNotFound() {
        TaskAdminUpdateRequestDto dto = new TaskAdminUpdateRequestDto(
                1L,
                "Обновлённый заголовок",
                "Обновлённое описание",
                Status.IN_PROGRESS,
                Priority.MEDIUM,
                2L
        );

        Long authorId = 1L;

        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> taskService.update(dto, authorId));
    }

    @Test
    void update_shouldHandleDataAccessException() {
        TaskAdminUpdateRequestDto dto = new TaskAdminUpdateRequestDto(
                1L,
                "Обновлённый заголовок",
                "Обновлённое описание",
                Status.IN_PROGRESS,
                Priority.MEDIUM,
                2L
        );

        Long authorId = 1L;

        Task existingTask = new Task();
        existingTask.setId(1L);
        existingTask.setTitle("Старый заголовок");
        existingTask.setDescription("Старое описание");
        existingTask.setStatus(Status.WAITING);
        existingTask.setPriority(Priority.LOW);
        existingTask.setAuthor(Account.builder().id(authorId).build());
        existingTask.setAssignee(Account.builder().id(3L).build());

        Task mapperTask = new Task();
        mapperTask.setTitle(dto.title());
        mapperTask.setDescription(dto.description());
        mapperTask.setStatus(dto.status());
        mapperTask.setPriority(dto.priority());
        mapperTask.setAssignee(Account.builder().id(dto.assigneeId()).build());

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(taskMapperManager.toModel(dto)).thenReturn(mapperTask);
        doNothing().when(taskValidate).validate(dto, authorId);
        doThrow(new DataAccessException("Ошибка базы данных") {}).when(taskRepository).save(existingTask);

        PersistenceException exception = assertThrows(PersistenceException.class, () -> {
            taskService.update(dto, authorId);
        });

        assertEquals("Ошибка при сохранении задачи в базу данных", exception.getError().getText());
    }
}