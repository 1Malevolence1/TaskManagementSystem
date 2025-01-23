package com.example.TaskManagementSystem.security.authentication;

import jakarta.validation.constraints.NotBlank;

public record SingInRequestDto(


        String email,

        @NotBlank
        String password
) {
}
