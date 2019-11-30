package com.uni10.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "subjects", catalog = "courses")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String firstName;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @OneToMany(mappedBy = "subject")
    private Set<Course> courses;
}
