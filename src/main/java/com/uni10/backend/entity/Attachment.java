package com.uni10.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "attachments", catalog = "courses")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(name = "data")
    private Date data;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "course_id", nullable = false)
    private long courseId;

    @ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    @OneToMany(mappedBy = "attachment")
    private Set<Comment> comments;

}
