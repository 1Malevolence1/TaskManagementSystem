package com.example.TaskManagementSystem.account.service;

import com.example.TaskManagementSystem.account.dto.AccountCreateRequestDto;
import com.example.TaskManagementSystem.account.dto.AccountResponseDto;
import com.example.TaskManagementSystem.account.mapper.AccountMapperManager;
import com.example.TaskManagementSystem.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {


    private final AccountMapperManager mapper;
    private final AccountRepository repository;

    @Override
    public void create(AccountCreateRequestDto dto) {
        repository.save(
                mapper.toModel(dto)
        );
    }

    @Override
    public AccountResponseDto get(Long id) {
        return mapper.toDto(
                repository.findById(id).orElseThrow(() ->
                        new NoSuchElementException("Not found account with ID::%d".formatted(id)))
        );
    }
}
