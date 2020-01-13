package com.uni10.backend.security;

import com.uni10.backend.entity.Role;
import com.uni10.backend.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JWTUtil {

    public static final String ROLES = "ROLES";
    public static final String ID = "ID";

    @Value("${jwt.secret}")
    private String secret;

    public UserInfo extractUser(final String token) {
        final Claims claims = extractAllClaims(token);
        final List<String> roles = claims.get(ROLES, List.class);
        final long id = claims.get(ID, Long.class);
        final User user = new User()
                .setId(id)
                .setRole(Role.valueOf(roles.get(0)))
                .setUsername(claims.getSubject());
        return new UserInfo(user);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public String generateToken(UserInfo userInfo) {
        final Instant now = Instant.now();
        final Instant expiration = now.plusSeconds(10 * 60 * 60);
        val authorities = userInfo.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return Jwts.builder()
                .claim(ROLES, authorities)
                .claim(ID, userInfo.getId())
                .setSubject(userInfo.getUsername())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

}
