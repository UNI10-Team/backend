package com.uni10.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "attendance", catalog = "courses")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "student_id")
    private long studentId;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User student;

    @Column(name = "schedule_id")
    private long scheduleId;

    @ManyToOne
    @JoinColumn(name = "schedule_id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Schedule schedule;
}
