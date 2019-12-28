package com.uni10.backend.security;


import com.uni10.backend.entity.User;
import com.uni10.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SecurityService implements UserDetailsService {

    private UserRepository userRepository;
    private final ThreadLocal<User> actingUser = new ThreadLocal<>();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optional = userRepository.findByUsernameOrEmail(username, username);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        final User user = optional.get();
        actingUser.set(user);
        return new UserInfo(user);
    }

    public static UserInfo getPrincipal(){
        return (UserInfo) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
    }

    public User getCurrentUser(){
        return actingUser.get();
    }

}
