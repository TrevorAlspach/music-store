package com.example.musicstore.rest.dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class RegisterRequest {
    //@Getter
    private String email;
    //@Getter
    private String password;
    //@Getter
    private String username;
    //@Getter
    private String roles;
}
