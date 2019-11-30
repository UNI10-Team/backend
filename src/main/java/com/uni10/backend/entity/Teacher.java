package com.uni10.backend.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "teachers", catalog = "courses")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_id", nullable = false)
    private long userId;

}

