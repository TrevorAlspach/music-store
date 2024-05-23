package com.example.musicstore.repositories;

import com.example.musicstore.entities.Song;
import com.example.musicstore.entities.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends ListCrudRepository<Song, Long> {
    public List<Song> findByName(String songName);

}