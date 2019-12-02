package com.uni10.backend.repository;

import com.uni10.backend.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AttachmentRepository extends JpaRepository<Attachment, Long>,
        JpaSpecificationExecutor<Attachment> {
}
