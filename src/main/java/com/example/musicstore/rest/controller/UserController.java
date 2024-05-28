package com.example.musicstore.rest.controller;

import com.example.musicstore.entities.User;
import com.example.musicstore.repositories.UserRepository;
import com.example.musicstore.rest.dto.SpotifyRefreshTokenDTO;
import com.example.musicstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/spotifyRefreshToken")
    ResponseEntity<SpotifyRefreshTokenDTO> getSpotifyRefreshToken(Authentication authentication){
        User authenticatedUser = (User) authentication.getPrincipal();
        SpotifyRefreshTokenDTO tokenDTO = new SpotifyRefreshTokenDTO();
        tokenDTO.setToken(authenticatedUser.getSpotifyRefreshToken());
        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("/spotifyRefreshToken")
    ResponseEntity<SpotifyRefreshTokenDTO> updateSpotifyRefreshToken(Authentication authentication, SpotifyRefreshTokenDTO tokenDTO){
        User authenticatedUser = (User) authentication.getPrincipal();
        authenticatedUser.setSpotifyRefreshToken(tokenDTO.getToken());
        User updatedUser = userService.updateUser(authenticatedUser);
        return ResponseEntity.ok().build();
    }


}
