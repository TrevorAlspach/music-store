package com.example.musicstore.rest.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class TimestampDTO {

    Instant expiresAt;
}
