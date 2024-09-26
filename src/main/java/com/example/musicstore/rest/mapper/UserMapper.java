package com.example.musicstore.rest.mapper;

import com.example.musicstore.entities.Song;
import com.example.musicstore.entities.User;
import com.example.musicstore.rest.dto.SongDTO;
import com.example.musicstore.rest.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDTO user);
    UserDTO toDTO(User user);

    List<UserDTO> toDTOList(List<User> users);

    List<User> toEntityList(List<UserDTO> users);
}
