package com.example.TaskManagementSystem.task.controller;

import com.example.TaskManagementSystem.account.model.Account;
import com.example.TaskManagementSystem.task.dto.TaskAdminUpdateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskCreateRequestDto;
import com.example.TaskManagementSystem.task.serivce.TaskService;
import com.example.TaskManagementSystem.utils.BindingResultValidate;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/tasks")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Управление задачами (Админ)", description = "API для управления задачами. Доступно только для ADMIN")
@SecurityRequirement(name = "JWT")
public class TaskAdminRestController {

    private final TaskService taskService;
    private final BindingResultValidate bindingResultValidate;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(
            summary = "Создать задачу",
            description = "Создаёт новую задачу. Доступно только для ADMIN"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Задача успешно создана", content = @Content),
            @ApiResponse(responseCode = "400", description = "Некорректные входные данные", content = @Content),
            @ApiResponse(responseCode = "403", description = "Доступ запрещён. Требуется роль ADMIN.", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<Void> addTask(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные для создания задачи",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TaskCreateRequestDto.class))
            )
            @Valid @RequestBody TaskCreateRequestDto dto,
            BindingResult bindingResult,
            @AuthenticationPrincipal Account userDetails)  {
        log.info("start method by add Task. Dto: {}", dto);
        bindingResultValidate.check(bindingResult);
        taskService.create(dto, userDetails.getId());
        log.info("task  add");
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(
            summary = "Обновить задачу (Админ)",
            description = "Обновляет данные задачи. Доступно только для ADMIN. " +
                    "Все поля не обязательны. Если поле не указано, оно не будет обновлено."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Задача успешно обновлена", content = @Content),
            @ApiResponse(responseCode = "400", description = "Некорректные входные данные", content = @Content),
            @ApiResponse(responseCode = "403", description = "Доступ запрещён. Требуется роль ADMIN.", content = @Content)
    })
    @PutMapping("/update")
    public ResponseEntity<Void> updateAdminTask(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные для обновления задачи. Все поля не обязательны. " +
                            "Если поле не указано, оно не будет обновлено",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TaskAdminUpdateRequestDto.class))
            )
            @RequestBody TaskAdminUpdateRequestDto dto,
            @AuthenticationPrincipal Account userDetails
    ) {
        log.info("start method by updateAdminTask Task. Dto: {}", dto);
        taskService.update(dto, userDetails.getId());
        log.info("task update");
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(
            summary = "Удалить задачу",
            description = "Удаляет задачу по указанному ID. Доступно только для ADMIN"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Задача успешно удалена", content = @Content),
            @ApiResponse(responseCode = "404", description = "Задача не найдена", content = @Content),
            @ApiResponse(responseCode = "403", description = "Доступ запрещён. Требуется роль ADMIN.", content = @Content)
    })
    @DeleteMapping("/{taskId}/delete")
    public ResponseEntity<Void> deleteTask(
            @Parameter(description = "ID задачи для удаления", required = true, example = "1")
            @PathVariable(name = "taskId") Long id) {
        log.info("start method by delete Task with ID: {}", id);
        taskService.delete(id);
        log.info("task delete");
        return ResponseEntity.noContent().build();
    }
}