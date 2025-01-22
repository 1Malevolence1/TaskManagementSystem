package com.example.TaskManagementSystem.security.authentication;


import com.example.TaskManagementSystem.account.model.Account;
import com.example.TaskManagementSystem.security.jwt.JwtTokenGenerate;
import com.example.TaskManagementSystem.security.jwt.JwtTokenSingInResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationSingInProcessor {


    private final JwtTokenGenerate jwtTokenGenerate;

    public JwtTokenSingInResponseDto processor(Account userDetails){
        return jwtTokenGenerate.generateToken(userDetails);
    }
}
