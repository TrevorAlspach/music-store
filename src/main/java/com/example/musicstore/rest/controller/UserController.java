package com.example.musicstore.rest.controller;

import com.example.musicstore.entities.User;
import com.example.musicstore.rest.dto.LoginResponse;
import com.example.musicstore.rest.dto.SpotifyRefreshTokenDTO;
import com.example.musicstore.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/createOrFindUser")
    public ResponseEntity<LoginResponse> getOrCreateUser(@AuthenticationPrincipal Jwt jwt, Authentication authentication){
        this.userService.createOrFindUser(jwt.getClaimAsString("email"), (Collection<GrantedAuthority>) authentication.getAuthorities());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/spotifyRefreshToken")
    ResponseEntity<SpotifyRefreshTokenDTO> getSpotifyRefreshToken(@AuthenticationPrincipal Jwt jwt){
        User user = userService.parseJwtForUser(jwt);
        SpotifyRefreshTokenDTO tokenDTO = new SpotifyRefreshTokenDTO();
        tokenDTO.setToken(user.getSpotifyRefreshToken());
        logger.info("get token is " + tokenDTO.getToken());
        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("/spotifyRefreshToken")
    ResponseEntity<SpotifyRefreshTokenDTO> updateSpotifyRefreshToken( @RequestBody SpotifyRefreshTokenDTO tokenDTO, @AuthenticationPrincipal Jwt jwt){
        User user = userService.parseJwtForUser(jwt);
        user.setSpotifyRefreshToken(tokenDTO.getToken());
        User updatedUser = userService.updateUser(user);
        tokenDTO.setToken(updatedUser.getSpotifyRefreshToken());
        logger.info("updated token is" + updatedUser.getSpotifyRefreshToken());
        return ResponseEntity.ok(tokenDTO);
    }


}
