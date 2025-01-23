package com.example.TaskManagementSystem.account.model;


import com.example.TaskManagementSystem.comment.model.Comment;
import com.example.TaskManagementSystem.task.model.Task;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "account", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter

public class Account implements UserDetails {


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
    private List<Task> authoredTasks;


    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> assignedTasks;


    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", authoredTasks=" + authoredTasks +
                ", assignedTasks=" + assignedTasks +
                ", comments=" + comments +
                '}';
    }
}
