package com.example.TaskManagementSystem.security.authentication;


import com.example.TaskManagementSystem.account.model.Account;
import com.example.TaskManagementSystem.security.jwt.JwtService;
import com.example.TaskManagementSystem.security.jwt.JwtTokenReceivingThroughRefreshTokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationRefreshTokenProcessor {

    private final JwtService jwtService;

    public JwtTokenReceivingThroughRefreshTokenResponseDto processor(Account userDetails){
        return new JwtTokenReceivingThroughRefreshTokenResponseDto(jwtService.generateToken(userDetails));
    }
}
