package com.example.TaskManagementSystem.security.authentication;

public record SingInRequestDto(
        String email,
        String password
) {
}
