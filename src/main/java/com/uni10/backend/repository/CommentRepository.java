package com.uni10.backend.repository;

import com.uni10.backend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long>,
        JpaSpecificationExecutor<Comment> {

}
