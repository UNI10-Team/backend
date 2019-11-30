package com.uni10.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.Set;

@Data
@Entity
@Table(name = "schedules", catalog = "courses")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(name = "day")
    private String day;

    @Column(name = "from_time")
    //@Temporal(TemporalType.TIME)
    private Time fromTime;

    @Column(name = "to_time")
    //@Temporal(TemporalType.TIME)
    private Time toTime;

    @Column(name = "room")
    private String room;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "course_id", nullable = false)
    private long courseId;

    @ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    @OneToMany(mappedBy = "schedule")
    private Set<StudentSchedule> studentSchedules;

}
