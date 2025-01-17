package com.example.TaskManagementSystem.account.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account_role", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Role {

    @Id
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name", nullable = false, unique = true)
    private String name;


}
