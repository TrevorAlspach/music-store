package com.example.musicstore.rest.controller;

import com.example.musicstore.entities.User;
import com.example.musicstore.rest.dto.LoginRequest;
import com.example.musicstore.rest.dto.LoginResponse;
import com.example.musicstore.rest.dto.LogoutResponse;
import com.example.musicstore.rest.dto.RegisterRequest;
//import com.example.musicstore.security.JwtService;
import com.example.musicstore.services.AuthService;
import com.example.musicstore.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;


}