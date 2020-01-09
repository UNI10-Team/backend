package com.uni10.backend.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "enrolls", catalog = "courses")
public class Enroll {

    @EmbeddedId
    private EnrollId id;

    @Data
    @Accessors(chain = true)
    @Embeddable
    public static class EnrollId implements Serializable {
        @Column(name = "student_id")
        private long studentId;
        @Column(name = "subject_id")
        private long subjectId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private User student;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id", insertable = false, updatable = false)
    private Subject subject;


}
