package com.example.musicstore.services;

import com.example.musicstore.entities.Playlist;
import com.example.musicstore.entities.Song;
import com.example.musicstore.entities.User;
import com.example.musicstore.repositories.PlaylistRepository;
import com.example.musicstore.repositories.SongRepository;
import com.example.musicstore.rest.dto.PlaylistDTO;
import com.example.musicstore.rest.dto.SongDTO;
import com.example.musicstore.rest.mapper.PlaylistMapper;
import com.example.musicstore.rest.mapper.SongMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;

    private final PlaylistMapper playlistMapper = Mappers.getMapper(PlaylistMapper.class);

    public List<PlaylistDTO> getPlaylistsForUser(User user){
        List<Playlist> playlists = playlistRepository.findByUser(user);
        return playlistMapper.toDTOList(playlists);
    }

    public PlaylistDTO createNewPlaylist(User user, PlaylistDTO playlistDTO){
        Playlist playlistEntity = playlistMapper.toEntity(playlistDTO);
        //Playlist savedPlaylist = playlistRepository.save(playlistEntity);
        playlistEntity.setUser(user);
        Playlist savedPlaylist = playlistRepository.save(playlistEntity);
        return playlistMapper.toDTO(savedPlaylist);
    }
}
