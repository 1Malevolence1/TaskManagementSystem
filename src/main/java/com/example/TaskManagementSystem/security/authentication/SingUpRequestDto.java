package com.example.TaskManagementSystem.security.authentication;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SingUpRequestDto {

    @Email
    private String email;

    @NotBlank
    private String password;
}
