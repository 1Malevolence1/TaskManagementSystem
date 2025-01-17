package com.example.TaskManagementSystem.account.model;


import com.example.TaskManagementSystem.comment.model.Comment;
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

    @Column(name = "account_email", nullable = false, unique = true)
    private String email;

    @Column(name = "account_password", nullable = false)
    private String password;


    @OneToOne()
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tasks;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;
}
