package com.example.TaskManagementSystem.security.jwt;


import com.example.TaskManagementSystem.account.service.AccountService;
import com.example.TaskManagementSystem.security.exception.ExpiredTokenException;
import com.example.TaskManagementSystem.security.exception.InvalidTokenException;
import com.example.TaskManagementSystem.utils.exception.Error;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtValidateToken {

    private final JwtService jwtService;
    private final AccountService accountService;

    public UserDetails validateToken(String token) {
        try {
            String email = jwtService.extractUsername(token);
            if (jwtService.isTokenExpired(token)) {
                throw new ExpiredTokenException(new Error("Срок действия токена истек"));
            }
            return accountService.getUserByEmail(email);
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException(new Error("Срок действия токена истек"));
        } catch (MalformedJwtException e) {
            throw new InvalidTokenException(new Error("Токен недействителен"));
        } catch (IllegalArgumentException e) {
            throw new InvalidTokenException(new Error("Токен отсутствует или пуст"));
        }
    }
}
