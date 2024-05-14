package com.example.musicstore.services;

import com.example.musicstore.entities.User;
import com.example.musicstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createNewUser(String name){
    return new User();
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
