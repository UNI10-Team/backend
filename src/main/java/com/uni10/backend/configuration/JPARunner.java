package com.uni10.backend.configuration;

import com.uni10.backend.entity.Role;
import com.uni10.backend.entity.User;
import com.uni10.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JPARunner implements CommandLineRunner {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public JPARunner(UserRepository userRepository,
                     BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUsername("csir2281");
        user.setPassword("Silviu");
        user.setEmail(user.getUsername() + "@scs.ubbcluj.ro");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_STUDENT);
        //userRepository.save(user);
    }
}
