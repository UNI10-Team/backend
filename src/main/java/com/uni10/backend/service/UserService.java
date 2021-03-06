package com.uni10.backend.service;

import com.uni10.backend.api.dto.UserDTO;
import com.uni10.backend.api.requests.RegistrationRequest;
import com.uni10.backend.entity.Role;
import com.uni10.backend.entity.User;
import com.uni10.backend.repository.UserRepository;
import com.uni10.backend.security.SecurityService;
import com.uni10.backend.security.UserInfo;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

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
        val user = userRepository.findByUsername(securityService.getCurrentUser().getUsername());
        return userDTO(user);
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

    private static User user(final RegistrationRequest request) {
        return new User()
                .setId(0)
                .setUsername(request.getUsername())
                .setEmail(request.getEmail())
                .setPassword(bCryptPasswordEncoder.encode(request.getPassword()))
                .setRole(Role.ROLE_STUDENT)
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName());
    }

    public User registerUser(final RegistrationRequest request) {
        val user = userRepository.findByUsername(request.getUsername());
        if (user != null) {
            throw new RuntimeException("An user with this username already exists!");
        } else {
            if (request.isValid()) {
                return userRepository.save(user(request));
            } else {
                throw new RuntimeException("Passwords do not match");
            }
        }
    }

    @Autowired
    private void setBCryptPasswordEncoder(final BCryptPasswordEncoder bCryptPasswordEncoder) {
        UserService.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
}
