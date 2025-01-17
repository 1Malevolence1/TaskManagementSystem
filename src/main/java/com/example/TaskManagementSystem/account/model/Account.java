package com.example.TaskManagementSystem.account.model;


import com.example.TaskManagementSystem.task.model.Task;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "account", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;


    @OneToOne()
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "authorId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private List<Task> tasks;
}
