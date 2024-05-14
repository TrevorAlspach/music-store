package com.example.musicstore.rest.dto;

import lombok.Getter;

public class LoginRequest {
    @Getter
    private String email;

    @Getter
    private String password;
}
