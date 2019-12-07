package com.uni10.backend.service;

import com.uni10.backend.api.dto.UserDTO;
import com.uni10.backend.demo.Person;
import com.uni10.backend.demo.PersonDTO;
import com.uni10.backend.entity.Role;
import com.uni10.backend.entity.User;
import com.uni10.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private SecurityService securityService;
    private static BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDTO save(final UserDTO userDTO) {
        User user = user(userDTO);
        user = userRepository.save(user);
        return userDTO(user);
    }

    public Optional<UserDTO> update(final UserDTO userDTO, final long id) {
        if (userRepository.existsById(id)) {
            User user = user(userDTO);
            user.setId(id);
            user = userRepository.save(user);
            return Optional.of(userDTO(user));
        } else {
            return Optional.empty();
        }
    }

    public UserDTO getCurrentUser() {
        return userDTO(securityService.getCurrentUser());
    }

    private static UserDTO userDTO(final User user) {
        return new UserDTO()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setRole(user.getRole())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName());
    }

    private static User user(final UserDTO userDTO) {
        return new User()
                .setId(userDTO.getId())
                .setUsername(userDTO.getUsername())
                .setEmail(userDTO.getEmail())
                .setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()))
                .setRole(userDTO.getRole())
                .setFirstName(userDTO.getFirstName())
                .setLastName(userDTO.getLastName());
    }

    @Autowired
    private void setBCryptPasswordEncoder(final BCryptPasswordEncoder bCryptPasswordEncoder) {
        UserService.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
}
