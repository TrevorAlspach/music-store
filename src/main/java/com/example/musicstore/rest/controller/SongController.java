package com.example.musicstore.rest.controller;

import com.example.musicstore.entities.Song;
import com.example.musicstore.rest.dto.SongDTO;
import com.example.musicstore.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/song")
public class SongController {
    @Autowired
    private SongService songService;



    @GetMapping("/findByName")
    ResponseEntity<List<SongDTO>> findSongsByName(@RequestParam(name = "name") String name){
        List<SongDTO> songs = songService.getSongsByName(name);
        return ResponseEntity.ok(songs);
    }

    @PostMapping("/addSong")
    ResponseEntity<SongDTO> addSong(@RequestBody SongDTO song){
        SongDTO createdSong = songService.createSong(song);
        return ResponseEntity.ok(createdSong);
    }
}
