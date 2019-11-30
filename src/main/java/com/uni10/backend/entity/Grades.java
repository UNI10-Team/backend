package com.uni10.backend.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "grades", catalog = "courses")
public class Grades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;
}
