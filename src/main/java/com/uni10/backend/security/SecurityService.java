package com.uni10.backend.security;


import com.uni10.backend.api.requests.RegistrationRequest;
import com.uni10.backend.entity.User;
import com.uni10.backend.repository.UserRepository;
import com.uni10.backend.service.UserInfo;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class SecurityService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optional = userRepository.findByUsernameOrEmail(username, username);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return new UserInfo(optional.get());
    }

    public User getCurrentUser(){
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return userRepository.findByUsername(userInfo.getUsername());
    }

    public void registerUser(RegistrationRequest registrationRequest){

    }
}
