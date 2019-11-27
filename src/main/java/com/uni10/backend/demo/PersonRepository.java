package com.uni10.backend.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PersonRepository extends JpaRepository<Person, Long>,
        JpaSpecificationExecutor<Person> {
}
