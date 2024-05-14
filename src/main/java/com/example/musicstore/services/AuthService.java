package com.example.musicstore.services;

import com.example.musicstore.entities.User;
import com.example.musicstore.repositories.UserRepository;
import com.example.musicstore.rest.dto.LoginRequest;
import com.example.musicstore.rest.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    public User signup(RegisterRequest input) {
        User user = new User();
        user.setUsername(input.getUsername());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRoles(input.getRoles());

        return userRepository.save(user);
    }

    public User authenticate(LoginRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }
}
