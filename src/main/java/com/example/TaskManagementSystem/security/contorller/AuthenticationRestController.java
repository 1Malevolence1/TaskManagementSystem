package com.example.TaskManagementSystem.security.contorller;



import com.example.TaskManagementSystem.account.dto.AccountCreateRequestDto;
import com.example.TaskManagementSystem.security.authentication.AuthenticationService;
import com.example.TaskManagementSystem.security.authentication.SingInRequestDto;
import com.example.TaskManagementSystem.security.authentication.SingUpRequestDto;
import com.example.TaskManagementSystem.security.jwt.JwtTokenReceivingThroughRefreshTokenResponseDto;
import com.example.TaskManagementSystem.security.jwt.JwtTokenSingInResponseDto;
import com.example.TaskManagementSystem.utils.BindingResultValidate;
import com.example.TaskManagementSystem.utils.exception.Error;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Authentication API", description = "API для аутентификации и управления токенами")
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {

    private final AuthenticationService authenticationService;
    private final BindingResultValidate bindingResultValidate;
    @Operation(
            summary = "Аутентификация пользователя",
            description = "Метод для аутентификации пользователя и получения JWT токенов (access и refresh)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Аутентификация прошла успешно", content = @Content(schema = @Schema(implementation = JwtTokenSingInResponseDto.class))),
            @ApiResponse(responseCode = "403", description = "Неверное имя пользователя или пароль", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @PostMapping("/singIn")
    public ResponseEntity<JwtTokenSingInResponseDto> singIn(@Valid @RequestBody SingInRequestDto dto, BindingResult bindingResult) {
        bindingResultValidate.check(bindingResult);
        return ResponseEntity.ok(authenticationService.authenticate(dto));
    }


    @Operation(
            summary = "Обновление access токена",
            description = "Метод для обновления access токена с использованием refresh токена"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Токен успешно обновлен", content = @Content(schema = @Schema(implementation = JwtTokenReceivingThroughRefreshTokenResponseDto.class))),
            @ApiResponse(responseCode = "403", description = "Недействительный refresh токен", content = @Content(schema = @Schema(implementation = Error.class))
            )
    })
    @PutMapping("/refresh")
    public ResponseEntity<JwtTokenReceivingThroughRefreshTokenResponseDto> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }
}
