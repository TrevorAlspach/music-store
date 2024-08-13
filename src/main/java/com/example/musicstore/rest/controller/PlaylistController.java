package com.example.musicstore.rest.controller;

import com.example.musicstore.entities.Playlist;
import com.example.musicstore.entities.User;
import com.example.musicstore.rest.dto.PlaylistDTO;
import com.example.musicstore.rest.mapper.PlaylistMapper;
//import com.example.musicstore.security.JwtService;
import com.example.musicstore.security.auth0.UserJwtAuthenticationToken;
import com.example.musicstore.services.PlaylistService;
import com.example.musicstore.services.SongService;
import com.example.musicstore.services.UserService;
import jakarta.websocket.server.PathParam;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

    private final static Logger logger = LoggerFactory.getLogger(PlaylistController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PlaylistService playlistService;

    private final PlaylistMapper playlistMapper = Mappers.getMapper(PlaylistMapper.class);

    @GetMapping("/allPlaylists")
    public ResponseEntity<List<PlaylistDTO>> getAllPlaylistsForUser(@AuthenticationPrincipal Jwt jwt){
        User user = userService.parseJwtForUser(jwt);

        List<PlaylistDTO> playlists = playlistService.getPlaylistsForUser(user);

        return ResponseEntity.ok(playlists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistDTO> getPlaylist(@AuthenticationPrincipal Jwt jwt, @PathVariable String id){
        User user = userService.parseJwtForUser(jwt);

        Optional<PlaylistDTO> playlistDTO = playlistService.getPlaylistFromId(id);

        return playlistDTO.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/deletePlaylist/{id}")
    public ResponseEntity<PlaylistDTO> deletePlaylist(@AuthenticationPrincipal Jwt jwt, @PathVariable String id){
        User user = userService.parseJwtForUser(jwt);

        Optional<PlaylistDTO> playlistDTO = playlistService.deletePlaylist(id);

        return playlistDTO.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @PostMapping("/createPlaylist")
    public ResponseEntity<PlaylistDTO> createNewPlaylist(@AuthenticationPrincipal Jwt jwt, @RequestBody PlaylistDTO playlistDTO){
        User user = userService.parseJwtForUser(jwt);

        PlaylistDTO createdPlaylist = playlistService.createNewPlaylist(user, playlistDTO);
        return ResponseEntity.ok(createdPlaylist);
    }

    @PostMapping("/createPlaylistWithSongs")
    public ResponseEntity<PlaylistDTO> createNewPlaylistWithSongs(@AuthenticationPrincipal Jwt jwt, @RequestBody PlaylistDTO playlistDTO){
        User user = userService.parseJwtForUser(jwt);

        PlaylistDTO createdPlaylist = playlistService.createNewPlaylist(user, playlistDTO);
        return ResponseEntity.ok(createdPlaylist);
    }
}
