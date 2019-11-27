package com.uni10.backend.repository;

import com.uni10.backend.entity.I18NResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface I18NResourceRepository extends JpaRepository<I18NResource, Long>,
        JpaSpecificationExecutor<I18NResource> {

}
