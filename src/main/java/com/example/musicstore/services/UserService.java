package com.example.musicstore.services;

import com.example.musicstore.config.ExternalService;
import com.example.musicstore.entities.User;
import com.example.musicstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createOrFindUser(String email, String oauthId,Collection<GrantedAuthority> authorities){
        Optional<User> userOpt = findUserByEmail(email);

        if (!userOpt.isPresent()){
            User user = new User();
            //user.setId(sub);
            user.setEmail(email);
            user.setUsername(email);
            user.setOauthId(oauthId);
            //user.setRoles();
            return createNewUser(user);
        } else {
            return userOpt.get();
        }
    }

    public List<ExternalService> getConnectedServicesForUser(User user){
        List<ExternalService> connectedServices = new ArrayList<>();

        if (user.getSpotifyRefreshToken() != null){
            connectedServices.add(ExternalService.SPOTIFY);
        }

        if (user.getAppleMusicRefreshToken() != null){
            connectedServices.add(ExternalService.APPLE_MUSIC);
        }

        if (user.getYoutubeMusicRefreshToken() != null){
            connectedServices.add(ExternalService.YOUTUBE_MUSIC);
        }

        return connectedServices;

    }

    public User parseJwtForUser(Jwt jwt){
        String email = jwt.getClaimAsString("email");

        return findUserByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Email: "+email+" does not exist"));
    }

    public User createNewUser(User user){
        return this.userRepository.save(user);
    }

    public void deleteUser(User user){
        this.userRepository.delete(user);
    }

    public Optional<User> findUserByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }
}
