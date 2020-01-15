package com.uni10.backend.repository;

import com.uni10.backend.entity.Notice;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import javax.transaction.Transactional;

@Transactional
public interface NoticeRepository extends JpaRepositoryImplementation<Notice, Long> {
}
