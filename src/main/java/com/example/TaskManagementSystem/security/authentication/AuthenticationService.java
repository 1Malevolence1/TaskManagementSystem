package com.example.TaskManagementSystem.security.authentication;



import com.example.TaskManagementSystem.account.model.Account;
import com.example.TaskManagementSystem.security.jwt.JwtTokenReceivingThroughRefreshTokenResponseDto;
import com.example.TaskManagementSystem.security.jwt.JwtTokenSingInResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final AuthenticationFindUserDetails authenticationFindUserDetails;
    private final AuthenticationSingInDateValidator authenticationSingInDateValidator;
    private final AuthenticationSingInProcessor authenticationSingInProcessor;
    private final AuthenticationRefreshTokenProcessor authenticationRefreshTokenProcessor;

    public JwtTokenSingInResponseDto authenticate(SingInRequestDto dto) {
        authenticationSingInDateValidator.validate(dto);
        return authenticationSingInProcessor.processor(
                authenticationFindUserDetails.findUserDetailsByEmail(dto.email())
        );
    }

    public JwtTokenReceivingThroughRefreshTokenResponseDto refreshToken(String token){
       Account userDetails = authenticationFindUserDetails.findUserDetailsByToken(token);
       return authenticationRefreshTokenProcessor.processor(userDetails);

    }
}

