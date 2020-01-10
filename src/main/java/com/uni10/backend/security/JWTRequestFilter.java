package com.uni10.backend.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JWTRequestFilter extends OncePerRequestFilter {

    private SecurityService securityService;
    private JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException, ExpiredJwtException, SignatureException {


        if (!request.getRequestURI().equals("/authenticate")) {
            final String authorization = request.getHeader("Authorization");
            if (authorization != null) {
                final String jwt = authorization.substring(7);
                final UserInfo userInfo = jwtUtil.extractUser(jwt);

                securityService.setCurrentUser(userInfo);
                var token = new AuthenticationToken(userInfo);
                token.setDetails(new AuthenticationToken.AuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
        filterChain.doFilter(request, response);
    }
}
