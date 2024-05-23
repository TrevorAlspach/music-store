package com.example.musicstore.rest.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class SongDTO {
    @Getter
    String name;

    @Getter
    String artistName;

    @Getter
    String albumName;
}
