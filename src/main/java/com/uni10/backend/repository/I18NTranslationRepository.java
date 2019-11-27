package com.uni10.backend.repository;

import com.uni10.backend.entity.I18NTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface I18NTranslationRepository extends JpaRepository<I18NTranslation, Long>,
        JpaSpecificationExecutor<I18NTranslation> {


}
