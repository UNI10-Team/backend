package com.uni10.backend.controller;

import com.uni10.backend.api.requests.AuthenticationRequest;
import com.uni10.backend.api.requests.AuthenticationResponse;
import com.uni10.backend.api.requests.RegistrationRequest;
import com.uni10.backend.security.JWTUtil;
import com.uni10.backend.security.SecurityService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Api
@Controller
@RequestMapping("/")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;
    private SecurityService securityService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorrect credentials", ex);
        }

        final UserDetails userDetails = securityService.loadUserByUsername(authenticationRequest.getUsername());
        return ResponseEntity.ok(new AuthenticationResponse(jwtUtil.generateToken(userDetails)));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegistrationRequest registrationRequest) throws Exception {
        //securityService.registerUser(registrationRequest);
        return ResponseEntity.ok().build();
    }

}
