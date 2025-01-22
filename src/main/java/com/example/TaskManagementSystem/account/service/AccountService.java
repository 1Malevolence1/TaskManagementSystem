package com.example.TaskManagementSystem.account.service;

import com.example.TaskManagementSystem.account.dto.AccountCreateRequestDto;
import com.example.TaskManagementSystem.account.dto.AccountRegistrationRequestDto;
import com.example.TaskManagementSystem.account.dto.AccountResponseDto;

public interface AccountService {

    void create(AccountRegistrationRequestDto dto);
    void create(AccountCreateRequestDto dto);
    boolean exits(Long accountId);
    AccountResponseDto get(Long id);
}
