package com.example.musicstore.security;

import com.example.musicstore.entities.User;
import com.example.musicstore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagerConfig implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("Email: "+email+" does not exist"));
    }

    /*UserDetails loadUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }*/
}