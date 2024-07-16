package com.example.musicstore.api;

import com.example.musicstore.entities.Song;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiscogsService {

    @Value("${discogs_token}")
    String discogs_token;

    String baseUrl = "https://api.discogs.com/database/search?";

    RestClient client;

    @PostConstruct
    public void postConstruct(){
        client = RestClient.builder()
                //.baseUrl("https://api.discogs.com/database/search")
                .defaultHeader("Authorization", "Discogs token=" + discogs_token)
                .build();
    }



    public List<Song> searchTrackByTitleAndArtist(String title, String artist){
        URI uri = UriComponentsBuilder.fromUriString(baseUrl + "track={track}&artist={artist}").build(title, artist);

        String response = client.get().uri(uri).retrieve().body(String.class);
        System.out.println(response);

        return new ArrayList<>();
    }
}
