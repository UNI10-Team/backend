package com.uni10.backend.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

public class AuthenticationToken implements Authentication {

    private UserInfo userInfo;
    private AuthenticationDetails details;

    public AuthenticationToken(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userInfo.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return userInfo.getPassword();
    }

    @Override
    @Deprecated
    public Object getDetails() {
        return details;
    }

    public AuthenticationDetails getAuthenticationDetails() {
        return details;
    }

    @Override
    public Object getPrincipal() {
        return userInfo.getUsername();
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        System.out.println("setAuthenticated(" + isAuthenticated + ")");
    }

    @Override
    public String getName() {
        return userInfo.getUsername();
    }

    public void setDetails(AuthenticationDetails details) {
        this.details = details;
    }

    public static class AuthenticationDetails {

        public AuthenticationDetails(HttpServletRequest request) {

        }
    }
}
