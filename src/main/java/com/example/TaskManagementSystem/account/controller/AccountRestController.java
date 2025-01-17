package com.example.TaskManagementSystem.account.controller;


import com.example.TaskManagementSystem.account.dto.AccountResponseDto;
import com.example.TaskManagementSystem.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/account")
@RequiredArgsConstructor
@Slf4j
public class AccountRestController {

    private final AccountService accountService;

    @GetMapping("{accountId:\\d+}")
    public ResponseEntity<AccountResponseDto> getTask(@PathVariable(name = "accountId") Long id){
        return ResponseEntity.ok(
                accountService.get(id)
        );
    }
}
