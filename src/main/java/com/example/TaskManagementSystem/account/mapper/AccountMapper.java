package com.example.TaskManagementSystem.account.mapper;

import com.example.TaskManagementSystem.account.dto.AccountCreateRequestDto;
import com.example.TaskManagementSystem.account.dto.AccountResponseDto;
import com.example.TaskManagementSystem.account.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {
    Account toEntity(AccountCreateRequestDto accountCreateRequestDto);

    AccountCreateRequestDto toAccountCreateRequestDto(Account account);

    Account toEntity(AccountResponseDto accountResponsetDto);

    AccountResponseDto toAccountResponsetDto(Account account);
}