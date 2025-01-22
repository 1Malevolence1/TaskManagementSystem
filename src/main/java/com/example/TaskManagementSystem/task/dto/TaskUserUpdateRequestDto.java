package com.example.TaskManagementSystem.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for {@link com.example.TaskManagementSystem.task.model.Task}
 */
public record TaskUserUpdateRequestDto(
        @NotNull
        Long id,

        @NotBlank
        String status) {
}