package com.example.TaskManagementSystem.account.repository;

import com.example.TaskManagementSystem.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {



    Optional<Account>  findByEmail(String email);


    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Account a WHERE a.email = :email")
    boolean existsByEmail(@Param("email") String email);
}
