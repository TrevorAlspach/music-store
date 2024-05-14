package com.example.musicstore.rest.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Data
public class LoginResponse {
    @NonNull
    private String token;
    @NonNull
    private long expiresIn;

}
