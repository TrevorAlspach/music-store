package com.example.musicstore.rest.controller;

import com.example.musicstore.entities.Playlist;
import com.example.musicstore.entities.User;
import com.example.musicstore.rest.dto.PlaylistDTO;
import com.example.musicstore.rest.mapper.PlaylistMapper;
import com.example.musicstore.security.JwtService;
import com.example.musicstore.services.PlaylistService;
import com.example.musicstore.services.SongService;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

    private final static Logger logger = LoggerFactory.getLogger(PlaylistController.class);

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PlaylistService playlistService;

    private final PlaylistMapper playlistMapper = Mappers.getMapper(PlaylistMapper.class);

    @GetMapping("/allPlaylists")
    public ResponseEntity<List<PlaylistDTO>> getAllPlaylistsForUser(Authentication authentication){
        User authenticatedUser = (User) authentication.getPrincipal();

        List<PlaylistDTO> playlists = playlistService.getPlaylistsForUser(authenticatedUser);

        return ResponseEntity.ok(playlists);
    }

    @PostMapping("/createPlaylist")
    public ResponseEntity<PlaylistDTO> createNewPlaylist(Authentication authentication, @RequestBody PlaylistDTO playlistDTO){
        User authenticatedUser = (User) authentication.getPrincipal();

        PlaylistDTO createdPlaylist = playlistService.createNewPlaylist(authenticatedUser, playlistDTO);
        return ResponseEntity.ok(createdPlaylist);
    }

    @PostMapping("/createPlaylistWithSongs")
    public ResponseEntity<PlaylistDTO> createNewPlaylistWithSongs(Authentication authentication, @RequestBody PlaylistDTO playlistDTO){
        User authenticatedUser = (User) authentication.getPrincipal();

        PlaylistDTO createdPlaylist = playlistService.createNewPlaylist(authenticatedUser, playlistDTO);
        return ResponseEntity.ok(createdPlaylist);
    }
}
