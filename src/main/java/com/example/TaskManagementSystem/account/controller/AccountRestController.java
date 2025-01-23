package com.example.TaskManagementSystem.account.controller;


import com.example.TaskManagementSystem.account.dto.AccountCreateRequestDto;
import com.example.TaskManagementSystem.account.dto.AccountResponseDto;
import com.example.TaskManagementSystem.account.model.Role;
import com.example.TaskManagementSystem.account.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/account")
@RequiredArgsConstructor
@Tag(name = "Управление аккаунтами", description = "API для управления пользовательскими аккаунтами")
public class AccountRestController {

    private final AccountService accountService;

    @Operation(summary = "Получить аккаунт по ID", description = "Возвращает данные аккаунта по указанному ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Аккаунт найден", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AccountResponseDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Аккаунт не найден", content = @Content)
    })
    @GetMapping("/{accountId:\\d+}")
    public ResponseEntity<AccountResponseDto> getAccount(
            @Parameter(description = "ID аккаунта для поиска", required = true, example = "1")
            @PathVariable(name = "accountId") Long id) {
        return ResponseEntity.ok(accountService.get(id));
    }

    @Operation(summary = "Создать новый аккаунт", description = "Создает новый аккаунт с предоставленными данными")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Аккаунт успешно создан", content = @Content),
            @ApiResponse(responseCode = "400", description = "Некорректные входные данные", content = @Content)
    })
    @PostMapping()
    public ResponseEntity<Void> addAccount(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные для создания аккаунта",
                    required = true,
                    content = @Content(schema = @Schema(implementation = AccountCreateRequestDto.class))
            )
            @Valid @RequestBody AccountCreateRequestDto dto) {
        accountService.create(dto);
        return ResponseEntity.noContent().build();
    }
}