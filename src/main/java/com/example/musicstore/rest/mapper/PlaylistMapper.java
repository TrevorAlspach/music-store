package com.example.musicstore.rest.mapper;

import com.example.musicstore.entities.Playlist;
import com.example.musicstore.entities.Song;
import com.example.musicstore.rest.dto.PlaylistDTO;
import com.example.musicstore.rest.dto.SongDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {
        Playlist toEntity(PlaylistDTO playlistDTO);
        PlaylistDTO toDTO(Playlist playlist);

        List<PlaylistDTO> toDTOList(List<Playlist> songs);

        List<Playlist> toEntityList(List<PlaylistDTO> songs);
}
