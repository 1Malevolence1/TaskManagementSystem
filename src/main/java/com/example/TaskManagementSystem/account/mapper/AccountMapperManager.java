package com.example.TaskManagementSystem.account.mapper;

import com.example.TaskManagementSystem.account.dto.AccountCreateRequestDto;
import com.example.TaskManagementSystem.account.dto.AccountResponseDto;
import com.example.TaskManagementSystem.account.model.Account;

public interface AccountMapperManager {

    Account toModel(AccountCreateRequestDto dto);
    AccountResponseDto toDto(Account dto);
}
