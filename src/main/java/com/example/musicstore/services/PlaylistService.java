package com.example.musicstore.services;

import com.example.musicstore.entities.Playlist;
import com.example.musicstore.entities.Song;
import com.example.musicstore.entities.User;
import com.example.musicstore.repositories.PlaylistRepository;
import com.example.musicstore.repositories.SongRepository;
import com.example.musicstore.rest.dto.PlaylistDTO;
import com.example.musicstore.rest.dto.SongDTO;
import com.example.musicstore.rest.exception.PlaylistAlreadyExistsException;
import com.example.musicstore.rest.mapper.PlaylistMapper;
import com.example.musicstore.rest.mapper.SongMapper;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;

    private final static Logger logger = LoggerFactory.getLogger(PlaylistService.class);

    private final PlaylistMapper playlistMapper = Mappers.getMapper(PlaylistMapper.class);

    public List<PlaylistDTO> getPlaylistsForUser(User user){
        List<Playlist> playlists = playlistRepository.findByUser(user);
        return playlistMapper.toDTOList(playlists);
    }

    public Optional<PlaylistDTO> getPlaylistFromId(String id){
        Optional<Playlist> playlist = playlistRepository.findById(Long.parseLong(id));

        return playlist.map(playlistMapper::toDTO);

    }

    public PlaylistDTO createNewPlaylist(User user, PlaylistDTO playlistDTO){
        Playlist playlistEntity = playlistMapper.toEntity(playlistDTO);
        playlistEntity.setUser(user);

        try{
            Playlist savedPlaylist = playlistRepository.save(playlistEntity);
            return playlistMapper.toDTO(savedPlaylist);
        } catch (DataIntegrityViolationException e){
            logger.error("Playlist Name must be unique, throwing error", e);
            throw new PlaylistAlreadyExistsException(e.getMessage());
        }
    }

    public Optional<PlaylistDTO> deletePlaylist(String id){
        return playlistRepository.findById(Long.parseLong(id)).
        map(playlist -> {
            playlistRepository.delete(playlist);
            return playlistMapper.toDTO(playlist);
        });
    }
}
