package com.example.TaskManagementSystem.account.mapper;

import com.example.TaskManagementSystem.account.dto.AccountCreateRequestDto;
import com.example.TaskManagementSystem.account.dto.AccountResponseDto;
import com.example.TaskManagementSystem.account.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {
    @Mapping(source = "role", target = "role.name")
    Account toEntity(AccountCreateRequestDto accountCreateRequestDto);
    

    Account toEntity(AccountResponseDto accountResponsetDto);

    AccountResponseDto toAccountResponsetDto(Account account);
}