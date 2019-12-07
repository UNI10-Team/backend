package com.uni10.backend.api.dto;

import com.uni10.backend.entity.Role;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel
@Data
@Accessors(chain = true)
public class UserDTO {

    private long id;

    private String username;

    private String email;

    private String password;

    private Role role;

    private String firstName;

    private String lastName;
}
