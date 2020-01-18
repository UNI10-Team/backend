package com.uni10.backend.api.requests;

import lombok.Data;

@Data
public class RegistrationRequest {

    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String repeatPassword;


    public boolean isValid(){
        return password.equals(repeatPassword);
    }
}
