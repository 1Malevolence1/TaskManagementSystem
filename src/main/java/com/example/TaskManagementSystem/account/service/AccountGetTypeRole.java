package com.example.TaskManagementSystem.account.service;


import com.example.TaskManagementSystem.account.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountGetTypeRole {
    
    private final AccountRoleValidator accountRoleValidator;
    
    public Role getRole(String role){
        accountRoleValidator.validateRole(role);
        return switch (role){
            case "ROLE_ADMIN" -> Role.builder().id(2L).build();
            case "ROLE_USER" ->  Role.builder().id(1L).build();
            default -> throw new IllegalStateException("Unexpected value: " + role);
        };
    }
}
