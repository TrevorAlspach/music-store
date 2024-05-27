package com.example.musicstore.rest.dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class PlaylistDTO {
    //@Getter
    //private String id;

    @Getter
    private String name;

    @Getter
    private String description;

    @Getter
    private String importSource;

    @Getter
    private List<SongDTO> songs;
}
