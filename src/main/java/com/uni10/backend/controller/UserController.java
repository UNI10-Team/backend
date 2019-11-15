package com.uni10.backend.controller;

import com.uni10.backend.entity.Role;
import com.uni10.backend.entity.User;
import com.uni10.backend.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public ResponseEntity save(User entity) {
        return ResponseEntity.ok(userService.save(entity));
    }

    @GetMapping("/currentUser")
    public ResponseEntity getCurrentUser(){
        return ResponseEntity.ok(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
    }
}
