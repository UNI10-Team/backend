package com.uni10.backend.service;


import com.uni10.backend.repository.UserRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SecurityService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optional = userRepository.findByUsername(username);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return new UserInfo(optional.get());
    }
}
