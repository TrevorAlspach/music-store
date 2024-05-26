package com.example.musicstore.rest.mapper;

import com.example.musicstore.entities.Song;
import com.example.musicstore.rest.dto.SongDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongMapper {
    Song toEntity(SongDTO songDTO);
    SongDTO toDTO(Song song);

    List<SongDTO> toDTOList(List<Song> songs);

    List<Song> toEntityList(List<SongDTO> songs);
}
