package com.uni10.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "schedules", catalog = "courses")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "day")
    private String day;

    @Column(name = "from_time")
    private Time fromTime;

    @Column(name = "to_time")
    private Time toTime;

    @Column(name = "room")
    private String room;

    @Column(name = "teacher_id", nullable = false)
    private long teacherId;

    @ManyToOne
    @JoinColumn(name = "teacher_id", insertable = false, updatable = false)
    private User teacher;

    @Column(name = "course_id", nullable = false)
    private long courseId;

    @ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Attendance> attendances = new HashSet<>();

}
