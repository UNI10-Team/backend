package com.uni10.backend.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comments", catalog = "courses")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "text")
    private String text;

    @Column(name = "user_id")
    private long userId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "attachment_id", nullable = false)
    private long attachmentId;

    @ManyToOne
    @JoinColumn(name = "attachment_id", insertable = false, updatable = false)
    private Attachment attachment;


}
