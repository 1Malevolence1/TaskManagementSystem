package com.example.TaskManagementSystem.task.model;


import com.example.TaskManagementSystem.account.model.Account;
import com.example.TaskManagementSystem.comment.model.Comment;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "task", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;


    @Column(name = "description", nullable = false, columnDefinition = "text")
    private String description;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "priority", nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    @ToString.Exclude
    private Account author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    @ToString.Exclude
    private Account assignee;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Comment> comments;
}
