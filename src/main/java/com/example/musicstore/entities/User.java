package com.example.musicstore.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

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

    @Column(name = "USER_NAME", nullable = false)
    private String username;

    @Column(nullable = false, name = "EMAIL", unique = true)
    private String email;

    @Column(nullable = false, name = "PASSWORD")
    private String password;

    @CreationTimestamp
    @Column(updatable = false, name = "CREATED_AT")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    @Column(nullable = false, name = "ROLES")
    private String roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays
                .stream(getRoles()
                        .split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();
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
