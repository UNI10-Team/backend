package com.uni10.backend.service;

import com.uni10.backend.entity.User;
import com.uni10.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User save(User entity) {
        return userRepository.save(entity);
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
