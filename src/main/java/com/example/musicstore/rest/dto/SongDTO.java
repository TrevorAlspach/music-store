package com.example.musicstore.rest.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class SongDTO {
    @Getter
    String name;

    @Getter
    String artist;

    @Getter
    String album;

    @Getter
    String releaseYear;

    @Getter
    String imageUrl;

    @Getter
    String time;
}
