package com.uni10.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "courses", catalog = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "subject_id", nullable = false)
    private long subjectId;

    @ManyToOne
    @JoinColumn(name = "subject_id", insertable = false, updatable = false)
    private Subject subject;

    @OneToMany(mappedBy = "course")
    private Set<Schedule> schedules;

    @OneToMany(mappedBy = "course")
    private Set<Attachment> attachments;
}
