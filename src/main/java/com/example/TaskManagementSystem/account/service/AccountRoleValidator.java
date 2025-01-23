package com.example.TaskManagementSystem.account.service;


import org.springframework.stereotype.Component;

@Component
public class AccountRoleValidator {

    public void validateRole(String role){
            if(role == null) throw new IllegalArgumentException("Поле роль не должно быть null");
            if(!role.equals("ROLE_USER") && !role.equals("ROLE_ADMIN")) throw new IllegalArgumentException("Поле роль должно быть ROLE_USER or ROLE_ADMIN");
        }
    }

