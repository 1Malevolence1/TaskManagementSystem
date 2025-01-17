package com.example.TaskManagementSystem.account.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AccountRegistrationRequestDto {
    private String email;
    private String password;
}
