package com.example.musicstore.rest.controller;

import com.example.musicstore.config.ExternalService;
import com.example.musicstore.entities.User;
import com.example.musicstore.rest.dto.ConnectedServiceDTO;
import com.example.musicstore.rest.dto.LoginResponse;
import com.example.musicstore.rest.dto.RefreshTokenDTO;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/createOrFindUser")
    public ResponseEntity<LoginResponse> getOrCreateUser(@AuthenticationPrincipal Jwt jwt, Authentication authentication){
        this.userService.createOrFindUser(jwt.getClaimAsString("email"), (Collection<GrantedAuthority>) authentication.getAuthorities());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/connectedServices")
    ResponseEntity<List<ConnectedServiceDTO>> getConnectedServices(@AuthenticationPrincipal Jwt jwt){
        User user = userService.parseJwtForUser(jwt);
        List<ConnectedServiceDTO> connectedServiceDTOs = new ArrayList<>();
        List<ExternalService> externalServices = userService.getConnectedServicesForUser(user);

        for (ExternalService externalService: externalServices){
            connectedServiceDTOs.add(new ConnectedServiceDTO(externalService));
        }
        return ResponseEntity.ok(connectedServiceDTOs);
    }

    @GetMapping("/spotifyRefreshToken")
    ResponseEntity<RefreshTokenDTO> getSpotifyRefreshToken(@AuthenticationPrincipal Jwt jwt){
        User user = userService.parseJwtForUser(jwt);
        RefreshTokenDTO tokenDTO = new RefreshTokenDTO();
        tokenDTO.setToken(user.getSpotifyRefreshToken());
        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("/spotifyRefreshToken")
    ResponseEntity<RefreshTokenDTO> updateSpotifyRefreshToken(@RequestBody RefreshTokenDTO tokenDTO, @AuthenticationPrincipal Jwt jwt){
        User user = userService.parseJwtForUser(jwt);
        user.setSpotifyRefreshToken(tokenDTO.getToken());
        User updatedUser = userService.updateUser(user);
        tokenDTO.setToken(updatedUser.getSpotifyRefreshToken());
        logger.info("updated token is" + updatedUser.getSpotifyRefreshToken());
        return ResponseEntity.ok(tokenDTO);
    }

    @GetMapping("/appleMusicRefreshToken")
    ResponseEntity<RefreshTokenDTO> getAppleMusicRefreshToken(@AuthenticationPrincipal Jwt jwt){
        User user = userService.parseJwtForUser(jwt);
        RefreshTokenDTO tokenDTO = new RefreshTokenDTO();
        tokenDTO.setToken(user.getAppleMusicRefreshToken());
        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("/appleMusicRefreshToken")
    ResponseEntity<RefreshTokenDTO> updateAppleMusicRefreshToken(@RequestBody RefreshTokenDTO tokenDTO, @AuthenticationPrincipal Jwt jwt){
        User user = userService.parseJwtForUser(jwt);
        user.setSpotifyRefreshToken(tokenDTO.getToken());
        User updatedUser = userService.updateUser(user);
        tokenDTO.setToken(updatedUser.getAppleMusicRefreshToken());
        return ResponseEntity.ok(tokenDTO);
    }


}
