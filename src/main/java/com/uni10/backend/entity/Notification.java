package com.uni10.backend.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "notifications", catalog = "courses")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(name = "text")
    private String text;

    @Column(name = "user_id", nullable = false)
    private long userId;

}
