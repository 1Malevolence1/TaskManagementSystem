package com.example.TaskManagementSystem.account.dto;

import com.example.TaskManagementSystem.account.model.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for {@link com.example.TaskManagementSystem.account.model.Account}
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class AccountCreateRequestDto {
    private String email;

    @NotBlank
    private String password;
    @NotBlank
    private String role;

}