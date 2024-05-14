package com.example.musicstore.rest;

import com.example.musicstore.entities.User;
import com.example.musicstore.rest.dto.LoginRequest;
import com.example.musicstore.rest.dto.LoginResponse;
import com.example.musicstore.rest.dto.RegisterRequest;
import com.example.musicstore.security.JwtService;
import com.example.musicstore.services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginUserDto) {
        User authenticatedUser = null;
        try{
             authenticatedUser = authService.authenticate(loginUserDto);
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header("Error", e.getMessage()).build();
        }

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);

    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerUserDto) {
        User registeredUser = authService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }
}
