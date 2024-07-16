package com.example.musicstore.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Table(name = "SONG")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ARTIST_NAME", nullable = false)
    private String artist;

    @Column(name = "ALBUM_NAME")
    private String album;

    @Column(name = "RELEASE_YEAR")
    private String releaseYear;

    @Column(name = "TIME")
    private String time;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    ///@ManyToMany(mappedBy = "song")
    //private Playlist playlist;
}
