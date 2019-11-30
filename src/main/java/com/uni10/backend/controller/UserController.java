package com.uni10.backend.controller;

import com.uni10.backend.api.dto.UserDTO;
import com.uni10.backend.entity.User;
import com.uni10.backend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.repository.config.RepositoryConfigurationSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/currentUser")
    public ResponseEntity<UserDTO> getCurrentUser(){
        val optional = userService.getCurrentUser();
        if(optional.isPresent()){
            return ResponseEntity.ok(optional.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
