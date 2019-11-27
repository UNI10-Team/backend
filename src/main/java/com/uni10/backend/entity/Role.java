package com.uni10.backend.entity;

import io.swagger.annotations.ApiModel;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_STUDENT,
    ROLE_TEACHER,
    ;


    @Override
    public String getAuthority() {
        return name();
    }
}
