package com.uni10.backend.configuration;

import com.uni10.backend.entity.I18NResource;
import com.uni10.backend.entity.I18NTranslation;
import com.uni10.backend.entity.Schedule;
import com.uni10.backend.repository.I18NResourceRepository;
import com.uni10.backend.repository.I18NTranslationRepository;
import com.uni10.backend.repository.ScheduleRepository;
import lombok.var;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends GlobalMethodSecurityConfiguration {

    @Bean
    public CommandLineRunner commandLineRunner(I18NResourceRepository resourceRepository,
                                               I18NTranslationRepository translationRepository){
        return str -> {

        };
    }
}
