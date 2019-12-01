package com.uni10.backend.configuration;

import com.uni10.backend.entity.Role;
import com.uni10.backend.entity.User;
import com.uni10.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JPARunner implements CommandLineRunner {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) {
        User user = new User();
        user.setUsername("uni10");
        user.setFirstName("UNI10");
        user.setLastName("ADMIN");
        user.setPassword("password");
        user.setEmail(user.getUsername() + "@scs.ubbcluj.ro");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_STUDENT);
        //userRepository.save(user);

        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_RESET = "\u001B[0m";
        System.out.println();
        System.out.println(ANSI_BLUE);
        System.out.println("De la Silviu:");
        System.out.println("If you want an account for yourself, go in com.uni10.backend.configuration.JPARunner");
        System.out.println("Change the username and password and uncomment //userRepository.save(user);");
        System.out.println(ANSI_RESET);
        System.out.println();
    }
}
