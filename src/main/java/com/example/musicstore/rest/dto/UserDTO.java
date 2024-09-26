package com.example.musicstore.rest.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;

@Data
public class UserDTO {

    String username;
    String email;
    Date createdAt;
    Date updatedAt;
    Long id;
    Collection<GrantedAuthority> authorities;
}
