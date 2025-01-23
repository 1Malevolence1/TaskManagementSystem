package com.example.TaskManagementSystem.account.controller;


import com.example.TaskManagementSystem.account.dto.AccountCreateRequestDto;
import com.example.TaskManagementSystem.account.dto.AccountResponseDto;
import com.example.TaskManagementSystem.account.model.Role;
import com.example.TaskManagementSystem.account.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/account")
@RequiredArgsConstructor
@Slf4j
public class AccountRestController {

    private final AccountService accountService;

    @GetMapping("/{accountId:\\d+}")
    public ResponseEntity<AccountResponseDto> getAccount(@PathVariable(name = "accountId") Long id){
        return ResponseEntity.ok(
                accountService.get(id)
        );
    }

    @PostMapping()
    public ResponseEntity<Void> addAccount(@Valid @RequestBody AccountCreateRequestDto dto){

        accountService.create(dto);
        return ResponseEntity.noContent().build();
    }
}
