package com.example.musicstore.rest.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class RefreshTokenDTO {
    @Getter
    private String token;
}
