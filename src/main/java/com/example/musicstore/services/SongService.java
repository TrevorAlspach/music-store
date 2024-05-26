package com.example.musicstore.services;

import com.example.musicstore.entities.Song;
import com.example.musicstore.repositories.SongRepository;
import com.example.musicstore.rest.mapper.SongMapper;
import com.example.musicstore.rest.dto.SongDTO;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    private final SongMapper songMapper = Mappers.getMapper(SongMapper.class);

    public List<Song> getAllSongs(){
        return songRepository.findAll();
    }

    public List<SongDTO> getSongsByName(String name){
        List<Song> songs = songRepository.findByName(name);
        return songMapper.toDTOList(songs);
    }

    public SongDTO createSong(SongDTO song){
        Song songEntity = songMapper.toEntity(song);
        Song savedSong = songRepository.save(songEntity);
        return songMapper.toDTO(savedSong);
    }
}
