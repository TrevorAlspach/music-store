package com.example.musicstore.rest.controller;

import com.example.musicstore.config.ExternalService;
import com.example.musicstore.entities.User;
import com.example.musicstore.rest.dto.ConnectedServiceDTO;
import com.example.musicstore.rest.dto.TimestampDTO;
import com.example.musicstore.rest.dto.TokenDTO;
import com.example.musicstore.rest.dto.UserDTO;
import com.example.musicstore.rest.mapper.UserMapper;
import com.example.musicstore.services.OktaManagementService;
import com.example.musicstore.services.UserService;
import org.mapstruct.factory.Mappers;
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
@RequestMapping("api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    @Autowired
    private UserService userService;

    @Autowired
    private OktaManagementService oktaManagementService;

    @GetMapping("/createOrFindUser")
    public ResponseEntity<UserDTO> getOrCreateUser(@AuthenticationPrincipal Jwt jwt, Authentication authentication){
        logger.info(jwt.getTokenValue());
        logger.info("ABOVE IS JWT");
        User user = this.userService.createOrFindUser(jwt.getClaimAsString("email"), jwt.getClaimAsString("sub"),(Collection<GrantedAuthority>) authentication.getAuthorities());
        UserDTO userDTO = this.userMapper.toDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<UserDTO> deleteUser(@AuthenticationPrincipal Jwt jwt, Authentication authentication){
        User user = userService.parseJwtForUser(jwt);
        //delete user data in syncify db
        this.userService.deleteUser(user);
        //use management api to delete user on okta side
        this.oktaManagementService.deleteUser(user.getOauthId());
        UserDTO userDTO = this.userMapper.toDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/connectedServices")
    ResponseEntity<List<ConnectedServiceDTO>> getConnectedServices(@AuthenticationPrincipal Jwt jwt){
        User user = userService.parseJwtForUser(jwt);
        List<ConnectedServiceDTO> connectedServiceDTOs = new ArrayList<>();
        List<ExternalService> externalServices = userService.getConnectedServicesForUser(user);
        //Boolean appleMusicExpired = userService.appleMusicExpiredForUser(user);

        for (ExternalService externalService: externalServices){
            //boolean expired = false;
            /*if (externalService.getDisplayName().equals(ExternalService.APPLE_MUSIC)){
                expired = appleMusicExpired;
            }*/
            connectedServiceDTOs.add(new ConnectedServiceDTO(externalService));
        }
        return ResponseEntity.ok(connectedServiceDTOs);
    }

    @GetMapping("/spotifyRefreshToken")
    ResponseEntity<TokenDTO> getSpotifyRefreshToken(@AuthenticationPrincipal Jwt jwt){
        User user = userService.parseJwtForUser(jwt);
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(user.getSpotifyRefreshToken());
        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("/spotifyRefreshToken")
    ResponseEntity<TokenDTO> updateSpotifyRefreshToken(@RequestBody TokenDTO tokenDTO, @AuthenticationPrincipal Jwt jwt){
        User user = userService.parseJwtForUser(jwt);
        user.setSpotifyRefreshToken(tokenDTO.getToken());
        User updatedUser = userService.updateUser(user);
        tokenDTO.setToken(updatedUser.getSpotifyRefreshToken());
        logger.debug("updated token is" + updatedUser.getSpotifyRefreshToken());
        return ResponseEntity.ok(tokenDTO);
    }

    @GetMapping("/appleMusicUserToken")
    ResponseEntity<TokenDTO> getAppleMusicUserToken( @AuthenticationPrincipal Jwt jwt){
        User user = userService.parseJwtForUser(jwt);
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(user.getAppleMusicUserToken());
        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("/appleMusicUserToken")
    ResponseEntity<TokenDTO> updateAppleMusicUserToken(@RequestBody TokenDTO tokenDTO, @AuthenticationPrincipal Jwt jwt){
        User user = userService.parseJwtForUser(jwt);
        user.setAppleMusicUserToken(tokenDTO.getToken());
        User updatedUser = userService.updateUser(user);
        tokenDTO.setToken(updatedUser.getAppleMusicUserToken());
        return ResponseEntity.ok(tokenDTO);
    }


}
