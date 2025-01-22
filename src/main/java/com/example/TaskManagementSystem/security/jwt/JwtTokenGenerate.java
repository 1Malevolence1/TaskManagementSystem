package com.example.TaskManagementSystem.security.jwt;


import com.example.TaskManagementSystem.account.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenGenerate {

    private final JwtService jwtService;

    public JwtTokenSingInResponseDto generateToken(Account userDetails){
        return new JwtTokenSingInResponseDto(
                jwtService.generateToken(userDetails),
                jwtService.generateRefreshToken(userDetails)
        );
    }
}
