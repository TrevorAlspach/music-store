package com.example.musicstore.security.auth0;

import com.example.musicstore.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;

public class UserJwtAuthenticationToken extends JwtAuthenticationToken {
    private final User user;

    public UserJwtAuthenticationToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities, User user) {
        super(jwt, authorities);
        this.user = user;
    }

    public User getUser() {
        return user;
    }


}
