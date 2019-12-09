package com.uni10.backend.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

public enum Role implements GrantedAuthority {

    ROLE_ADMIN(null),
    ROLE_SUBJECT_TEACHER(ROLE_ADMIN),
    ROLE_COURSE_TEACHER(ROLE_SUBJECT_TEACHER),
    ROLE_STUDENT(ROLE_COURSE_TEACHER),
    ;

    private Set<Role> children = new HashSet<>();

    Role(final Role parent){
        if(parent != null){
            parent.children.add(this);
        }
    }

    @Override
    public String getAuthority() {
        return name();
    }

    public Set<Role> getChildren() {
        return children;
    }
}
