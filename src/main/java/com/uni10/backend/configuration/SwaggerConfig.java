package com.uni10.backend.configuration;

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.uni10.backend.api.dto.AttachmentDTO;
import com.uni10.backend.api.dto.AttendanceDTO;
import com.uni10.backend.api.dto.CommentDTO;
import com.uni10.backend.api.dto.CourseDTO;
import com.uni10.backend.api.dto.I18NTranslationDTO;
import com.uni10.backend.api.dto.NotificationDTO;
import com.uni10.backend.api.dto.ScheduleDTO;
import com.uni10.backend.api.dto.SubjectDTO;
import com.uni10.backend.api.dto.UserDTO;
import com.uni10.backend.controller.UserController;
import com.uni10.backend.service.I18NResourceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@AllArgsConstructor
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.apis(RequestHandlerSelectors.basePackage("com.uni10.backend.controller"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
