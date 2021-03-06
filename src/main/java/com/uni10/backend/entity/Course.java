package com.uni10.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "courses", catalog = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "subject_id")
    private long subjectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Subject subject;

    @OneToMany(mappedBy = "course")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Schedule> schedules = new HashSet<>();

    @OneToOne(mappedBy = "course", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Attachment attachment;
}
