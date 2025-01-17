package com.example.TaskManagementSystem.account.mapper;

import com.example.TaskManagementSystem.account.dto.AccountCreateRequestDto;
import com.example.TaskManagementSystem.account.dto.AccountResponseDto;
import com.example.TaskManagementSystem.account.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AccountMapperManagerImpl implements AccountMapperManager {

    private final AccountMapper accountMapper;

    @Override
    public Account toModel(AccountCreateRequestDto dto) {
        return accountMapper.toEntity(dto);
    }

    @Override
    public AccountResponseDto toDto(Account dto) {
        return accountMapper.toAccountResponsetDto(dto);
    }
}
