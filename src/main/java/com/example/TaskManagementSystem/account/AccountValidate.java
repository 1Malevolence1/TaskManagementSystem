package com.example.TaskManagementSystem.account;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountValidate {

    private final AccountExistValidate accountExistValidate;

    public void validateAccountExists(Long accountId){
        accountExistValidate.validateExistsAccountById(accountId);
    }
}
