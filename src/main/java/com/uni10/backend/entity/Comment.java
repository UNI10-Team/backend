package com.uni10.backend.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comments", catalog = "courses")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(name = "text")
    private String text;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "attachment_id", nullable = false)
    private long attachmentId;

    @ManyToOne
    @JoinColumn(name = "attachment_id", insertable = false, updatable = false)
    private Attachment attachment;


}
