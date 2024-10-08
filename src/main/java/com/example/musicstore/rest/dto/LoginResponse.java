package com.example.musicstore.rest.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Data
public class LoginResponse {

    private String token;

    private long expiresIn;

}
