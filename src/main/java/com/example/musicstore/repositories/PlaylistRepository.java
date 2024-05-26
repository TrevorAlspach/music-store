package com.example.musicstore.repositories;

import com.example.musicstore.entities.Playlist;
import com.example.musicstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

     List<Playlist> findByUser(User user);

}
