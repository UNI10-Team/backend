package com.uni10.backend.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "attendance", catalog = "courses")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "schedule_id", nullable = false)
    private long scheduleId;

    @ManyToOne
    @JoinColumn(name = "schedule_id", insertable = false, updatable = false)
    private Schedule schedule;
}
