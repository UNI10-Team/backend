package com.uni10.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "subjects", catalog = "courses")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "teacher_id")
    private long teacherId;

    @ManyToOne(fetch = FetchType.EAGER)//todo
    @JoinColumn(name = "teacher_id", insertable = false, updatable = false)
    private User teacher;

    private int year;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Course> courses = new HashSet<>();
}
