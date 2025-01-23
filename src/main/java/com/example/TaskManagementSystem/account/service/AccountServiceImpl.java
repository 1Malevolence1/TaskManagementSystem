package com.example.TaskManagementSystem.account.service;

import com.example.TaskManagementSystem.account.dto.AccountCreateRequestDto;
import com.example.TaskManagementSystem.account.dto.AccountResponseDto;
import com.example.TaskManagementSystem.account.mapper.AccountMapperManager;
import com.example.TaskManagementSystem.account.model.Account;
import com.example.TaskManagementSystem.account.model.Role;
import com.example.TaskManagementSystem.account.repository.AccountRepository;
import com.example.TaskManagementSystem.security.exception.AccountAlreadyRegistered;
import com.example.TaskManagementSystem.utils.exception.Error;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {


    private final AccountMapperManager mapper;
    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;

    private final AccountGetTypeRole accountGetTypeRole;

    @Override
    @Transactional
    public void create(AccountCreateRequestDto dto) {
        Role role = accountGetTypeRole.getRole(dto.getRole());
        if(exitsByEmail(dto.getEmail())) throw new AccountAlreadyRegistered(new Error("Account with this email already exists"));
        Account account = mapper.toModel(dto);
        account.setRole(role);
        account.setPassword(passwordEncoder.encode(dto.getPassword()));
        repository.save(account);
    }

    @Override
    public boolean exitsById(Long accountId) {
        return repository.existsById(accountId);
    }

    @Override
    public boolean exitsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public AccountResponseDto get(Long id) {
        return mapper.toDto(
                repository.findById(id).orElseThrow(() ->
                        new NoSuchElementException("Not found account with ID::%d".formatted(id)))
        );
    }

    @Override
    public Account getUserByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Not found account with email::%s".formatted(email)));
    }
}
