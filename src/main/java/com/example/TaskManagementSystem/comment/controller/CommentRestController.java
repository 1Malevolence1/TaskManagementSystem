package com.example.TaskManagementSystem.comment.controller;

import com.example.TaskManagementSystem.account.model.Account;
import com.example.TaskManagementSystem.comment.dto.CommentCreateRequestDto;
import com.example.TaskManagementSystem.comment.dto.CommentResponseDto;
import com.example.TaskManagementSystem.comment.service.CommentService;
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

import java.util.List;

@RestController
@RequestMapping("api/task/comment")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Управление комментариями", description = "API для управления комментариями. Доступно только для авторизованных пользователей.")
@SecurityRequirement(name = "JWT")
public class CommentRestController {

    private final CommentService commentService;
    private final BindingResultValidate bindingResultValidate;


    @PreAuthorize("isAuthenticated()")
    @Operation(
            summary = "Создать комментарий",
            description = "Создаёт новый комментарий к задаче. Доступно только для авторизованных пользователей."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Комментарий успешно создан", content = @Content),
            @ApiResponse(responseCode = "400", description = "Некорректные входные данные", content = @Content),
            @ApiResponse(responseCode = "403", description = "Доступ запрещён. Требуется авторизация.", content = @Content)
    })
    @PostMapping("")
    public ResponseEntity<Void> createComment(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные для создания комментария",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CommentCreateRequestDto.class))
            )
            @Valid @RequestBody CommentCreateRequestDto dto,
            BindingResult bindingResult,


            @AuthenticationPrincipal Account userDetails) {
        log.info("start method by create Comment. Dto: {}", dto);

        bindingResultValidate.check(bindingResult);
        commentService.create(
                dto,
                userDetails.getId(),
                userDetails.getRole().getName()
        );

        log.info("comment create");
        log.info("method complete");
        return ResponseEntity.noContent().build();
    }


    @PreAuthorize("isAuthenticated()")
    @Operation(
            summary = "Удалить комментарий",
            description = "Удаляет комментарий по указанному ID. Доступно только для авторизованных пользователей."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Комментарий успешно удалён", content = @Content),
            @ApiResponse(responseCode = "404", description = "Комментарий не найден", content = @Content),
            @ApiResponse(responseCode = "403", description = "Доступ запрещён. Требуется авторизация.", content = @Content)
    })
    @DeleteMapping("{commentId:\\d+}")
    public ResponseEntity<Void> deleteComment(
            @Parameter(description = "ID комментария для удаления", required = true, example = "1")
            @PathVariable(name = "commentId") Long id,
            @Parameter(hidden = true)
            @AuthenticationPrincipal Account userDetails) {
        log.info("start method by delete Comment. ID: {}", id);
        commentService.delete(
                id, userDetails.getId());
        log.info("comment delete");
        log.info("method complete");
        return ResponseEntity.noContent().build();
    }


    @PreAuthorize("isAuthenticated()")
    @Operation(
            summary = "Получить комментарии для задачи",
            description = "Возвращает список комментариев для указанной задачи. Доступно только для авторизованных пользователей."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Комментарии найдены", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CommentResponseDto.class))
            }),
            @ApiResponse(responseCode = "403", description = "Доступ запрещён. Требуется авторизация.", content = @Content)
    })
    @GetMapping("")
    public ResponseEntity<List<CommentResponseDto>> getCommentsForTask(
            @Parameter(description = "ID задачи для получения комментариев", required = true, example = "1")
            @RequestParam(name = "taskId") Long id) {
        log.info("start method getAllCommentByTaskId. taskID: {}", id);
        List<CommentResponseDto> dto = commentService.getAllByTaskId(id);
        log.info("method complete");
        return ResponseEntity.ok(dto);
    }
}