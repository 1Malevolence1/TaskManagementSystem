package com.example.TaskManagementSystem.security.authentication;



import com.example.TaskManagementSystem.account.model.Account;
import com.example.TaskManagementSystem.account.service.AccountService;
import com.example.TaskManagementSystem.security.jwt.JwtService;
import com.example.TaskManagementSystem.security.jwt.JwtValidateToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationFindUserDetails {


    private final JwtValidateToken jwtValidateToken;
    private final AccountService accountService;
    private final JwtService jwtService;

    public Account findUserDetailsByEmail(String email){
        return findUserDetails(email);
    }

    public Account findUserDetailsByToken(String token){
        jwtValidateToken.validateToken(token);
        String email = jwtService.extractUsername(token);
        return findUserDetails(email);
    }

    private Account findUserDetails(String email){
        return accountService.getUserByEmail(email);
    }

}
