package com.example.TaskManagementSystem.account.repository;

import com.example.TaskManagementSystem.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
