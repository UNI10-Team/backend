package com.uni10.backend.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "grades", catalog = "courses")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
