package com.example.musicstore.rest.controller;

import com.example.musicstore.entities.User;
import com.example.musicstore.rest.dto.LoginRequest;
import com.example.musicstore.rest.dto.LoginResponse;
import com.example.musicstore.rest.dto.LogoutResponse;
import com.example.musicstore.rest.dto.RegisterRequest;
//import com.example.musicstore.security.JwtService;
import com.example.musicstore.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    Logger logger = LoggerFactory.getLogger(AuthController.class);
    //@Autowired
    //private JwtService jwtService;
    @Autowired
    private AuthService authService;

    @GetMapping("/isTokenValid")
    public ResponseEntity<Boolean> isTokenValid(@CookieValue(value = "token") String token){
        //return ResponseEntity.ok(!this.jwtService.isTokenExpired(token));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginUserDto) {
        /*User authenticatedUser = null;
        authenticatedUser = authService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        //LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
        HttpHeaders responseHeaders = new HttpHeaders();

        HttpCookie cookie = ResponseCookie.from("token", jwtToken).path("/").build();
        responseHeaders.add(HttpHeaders.SET_COOKIE, cookie.toString());


        return ResponseEntity.ok().headers(responseHeaders).build();*/

        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutResponse> logout(@CookieValue(value = "token") String token) {
        /*HttpHeaders responseHeaders = new HttpHeaders();
        HttpCookie cookie = ResponseCookie.from("token", token).path("/").maxAge(0).build();
        responseHeaders.add(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.ok().headers(responseHeaders).build();*/
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerUserDto) {
        /*User registeredUser = authService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);*/
        return ResponseEntity.ok().build();
    }
}
