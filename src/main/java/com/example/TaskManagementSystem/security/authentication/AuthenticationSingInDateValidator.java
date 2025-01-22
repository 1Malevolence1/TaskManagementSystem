package com.example.TaskManagementSystem.security.authentication;


import com.example.TaskManagementSystem.security.exception.BadRequestSingInCustomer;
import com.example.TaskManagementSystem.utils.exception.Error;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationSingInDateValidator {

    private final AuthenticationManager authenticationManager;

    public void validate(SingInRequestDto dto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    dto.email(),
                    dto.password())
            );
        } catch (BadCredentialsException e) {
            throw new BadRequestSingInCustomer(new Error("Неверное имя пользователя или пароль")
            );
        }
    }
}
