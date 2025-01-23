package com.example.TaskManagementSystem.account.utils;

import com.example.TaskManagementSystem.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class AccountExistValidate {

    private final AccountService accountService;

    public void validateExistsAccountById(Long accountId){
        if(!accountService.exitsById(accountId)) throw  new NoSuchElementException("Not found account with ID::%d".formatted(accountId));
    }
}
