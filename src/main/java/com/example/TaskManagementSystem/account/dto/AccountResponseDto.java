package com.example.TaskManagementSystem.account.dto;

import com.example.TaskManagementSystem.account.model.Role;

/**
 * DTO for {@link com.example.TaskManagementSystem.account.model.Account}
 */
public record AccountResponseDto(Long id, String email, String password, Role role) {
}