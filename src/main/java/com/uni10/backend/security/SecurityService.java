package com.uni10.backend.security;


import com.uni10.backend.entity.Role;
import com.uni10.backend.entity.User;
import com.uni10.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SecurityService implements UserDetailsService {

    private UserRepository userRepository;
    private final ThreadLocal<UserInfo> actingUser = new ThreadLocal<>();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optional = userRepository.findByUsernameOrEmail(username, username);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return new UserInfo(optional.get());
    }

    public void setCurrentUser(UserInfo userInfo) {
        actingUser.set(userInfo);
    }

    public UserInfo getCurrentUser() {
        return actingUser.get();
    }

    public boolean isUserInRole(final Role role) {
        return actingUser.get().getRole() == role;
    }

}
