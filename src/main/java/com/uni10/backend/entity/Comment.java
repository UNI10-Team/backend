package com.uni10.backend.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comments", catalog = "courses")
@Accessors(chain = true)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "text")
    private String text;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "isAccepted")
    private boolean isAccepted;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @Column(name = "attachment_id")
    private long attachmentId;


    @Column(name = "subject_id")
    private long subjectId;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attachment_id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Attachment attachment;
     */
}
