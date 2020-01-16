package com.uni10.backend.controller;

import com.uni10.backend.api.dto.UserDTO;
import com.uni10.backend.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/currentUser")
    public ResponseEntity<UserDTO> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "UserController.update", notes = "Update an existent user")
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO userDTO,
                                                  @PathVariable long id) {
        //Long id1 = Long.parseLong(id);
        return ResponseEntity.ok(userService.update(userDTO,id).get());
    }



}
