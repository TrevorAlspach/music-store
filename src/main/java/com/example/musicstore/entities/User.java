package com.example.musicstore.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Table(name = "USER_INFO")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "OAUTH_ID", nullable = false, unique = true)
    private String oauthId;

    @Column(name = "USER_NAME", nullable = false)
    private String username;

    @Column(nullable = false, name = "EMAIL")
    private String email;

    @Column( name = "PASSWORD")
    private String password;

    @CreationTimestamp
    @Column(updatable = false, name = "CREATED_AT")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    //@Column(nullable = false, name = "ROLES")
    //private List<GrantedAuthority> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Playlist> playlists;

    @Column(name = "SPOTIFY_REFRESH_TOKEN")
    private String spotifyRefreshToken;

    @Column(name = "APPLE_MUSIC_USER_TOKEN")
    private String appleMusicUserToken;

    @Column(name = "YOUTUBE_MUSIC_REFRESH_TOKEN")
    private String youtubeMusicRefreshToken;

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
