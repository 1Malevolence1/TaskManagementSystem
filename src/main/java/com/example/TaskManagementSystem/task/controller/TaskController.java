package com.example.TaskManagementSystem.task.controller;

import com.example.TaskManagementSystem.security.jwt.JwtService;
import com.example.TaskManagementSystem.task.dto.TaskResponseDto;
import com.example.TaskManagementSystem.task.dto.TaskUserUpdateRequestDto;
import com.example.TaskManagementSystem.task.model.Priority;
import com.example.TaskManagementSystem.task.model.Status;
import com.example.TaskManagementSystem.task.serivce.TaskService;
import com.example.TaskManagementSystem.utils.BindingResultValidate;
import com.example.TaskManagementSystem.utils.RequestHeaderManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/task")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Управление задачами", description = "API для управления задачами")
@SecurityRequirement(name = "JWT") // Указываем, что требуется JWT-аутентификация
public class TaskController {

    private final TaskService taskService;
    private final JwtService jwtService;
    private final BindingResultValidate bindingResultValidate;

    @PreAuthorize("isAuthenticated()")
    @Operation(
            summary = "Получить задачу по ID",
            description = "Возвращает задачу по указанному ID. Требуется JWT-аутентификация."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задача найдена", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TaskResponseDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Задача не найдена", content = @Content),
            @ApiResponse(responseCode = "403", description = "Доступ запрещён. Требуется авторизация.", content = @Content)
    })
    @GetMapping("/{taskId:\\d+}")
    public ResponseEntity<TaskResponseDto> getTask(
            @Parameter(description = "ID задачи для поиска", required = true, example = "1")
            @PathVariable(name = "taskId") Long id) {
        return ResponseEntity.ok(taskService.get(id));
    }



    @Operation(
            summary = "Обновить задачу",
            description = "Обновляет данные задачи. Требуется JWT-аутентификация."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Задача успешно обновлена", content = @Content),
            @ApiResponse(responseCode = "400", description = "Некорректные входные данные", content = @Content),
            @ApiResponse(responseCode = "403", description = "Доступ запрещён. Требуется авторизация.", content = @Content)
    })

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/account/update")
    public ResponseEntity<Void> updateUserTask(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные для обновления задачи",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TaskUserUpdateRequestDto.class))
            )
            @Valid @RequestBody TaskUserUpdateRequestDto dto,
            BindingResult bindingResult,
            @Parameter(hidden = true) @RequestHeader("Authorization") String token
            ) {
        log.info("start method updateUserTask. Dto: {}", dto);

        bindingResultValidate.check(bindingResult);
        taskService.update(dto, jwtService.extractUserId(RequestHeaderManager.extractTokenFromHeader(token)));

        log.info("task update");

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("isAuthenticated()")
    @Operation(
            summary = "Получить все задачи с фильтрацией",
            description = "Возвращает список задач с возможностью фильтрации по статусу, приоритету и ID аккаунта. Требуется JWT-аутентификация."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задачи найдены", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))
            }),
            @ApiResponse(responseCode = "403", description = "Доступ запрещён. Требуется авторизация.", content = @Content)
    })
    @GetMapping("/get/all")
    public ResponseEntity<Page<TaskResponseDto>> getFilterTakes(
            @Parameter(description = "ID аккаунта для фильтрации")
            @RequestParam(name = "accountId", required = false) Long id,

            @Parameter(description = "Статус задачи для фильтрации")
            @RequestParam(name = "status", required = false) Status status,

            @Parameter(description = "Приоритет задачи для фильтрации")
            @RequestParam(name = "priority", required = false) Priority priority,

            @Parameter(description = "Количество задач на странице", example = "10")
            @RequestParam(name = "size", defaultValue = "10") Integer size,

            @Parameter(description = "Номер страницы", example = "0")
            @RequestParam(name = "page", defaultValue = "0") Integer page) {

        return ResponseEntity.ok(taskService.getAllTasks(id, status, priority, size, page));
    }
}