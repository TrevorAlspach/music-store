package com.example.musicstore.config;

public enum ExternalService {
    SPOTIFY("Spotify"),
    APPLE_MUSIC("Apple Music"),
    YOUTUBE_MUSIC("YouTube Music");

    private final String displayName;

    ExternalService(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}