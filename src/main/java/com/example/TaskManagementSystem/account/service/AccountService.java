package com.example.TaskManagementSystem.account.service;

import com.example.TaskManagementSystem.account.dto.AccountCreateRequestDto;
import com.example.TaskManagementSystem.account.dto.AccountResponseDto;
import com.example.TaskManagementSystem.account.model.Account;
import com.example.TaskManagementSystem.account.model.Role;

public interface AccountService {


    void create(AccountCreateRequestDto dto);
    boolean exitsById(Long accountId);
    boolean exitsByEmail(String email);
    AccountResponseDto get(Long id);
    Account getUserByEmail(String email);
}
