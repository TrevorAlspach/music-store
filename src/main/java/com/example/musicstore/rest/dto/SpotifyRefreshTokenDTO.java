package com.example.musicstore.rest.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class SpotifyRefreshTokenDTO {
    @Getter
    private String token;
}
