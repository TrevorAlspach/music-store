package com.example.musicstore.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Table(name = "PLAYLIST")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SOURCE")
    private String source;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "SONG_COUNT")
    private int songCount;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Song> songs;

    @ManyToOne
    private User user;

}
