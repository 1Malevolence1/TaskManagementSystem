package com.example.TaskManagementSystem.security.jwt;

public record JwtTokenSingInResponseDto(
        String accessesToken,
        String refreshToken
) {
}
