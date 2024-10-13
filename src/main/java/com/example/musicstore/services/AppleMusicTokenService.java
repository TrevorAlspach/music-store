package com.example.musicstore.services;

import com.example.musicstore.entities.AppleMusicDeveloperToken;
import com.example.musicstore.repositories.AppleMusicDevTokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Service
public class AppleMusicTokenService {

    @Autowired
    private AppleMusicDevTokenRepository tokenRepository;

    @Value("${appleMusic.musickit.privateKey}")
    private String appleMusicPrivateKey;

    @Value("${appleMusic.musickit.keyId}")
    private String appleMusicKeyId;

    @Value("${appleMusic.musickit.teamId}")
    private String appleMusicTeamId;

    @Value("${appleMusic.musickit.origin}")
    private String origin;

    @Transactional(readOnly = true)
    public String getDeveloperToken() throws NoSuchAlgorithmException, InvalidKeySpecException {
        AppleMusicDeveloperToken existingToken = tokenRepository.findAll()
                .stream()
                .findFirst()
                .orElse(null);

        Instant now = Instant.now();

        // Check if the token exists and if it's expired
        if (existingToken != null && existingToken.getExpiresAt().isAfter(now)) {
            return existingToken.getToken(); // Return the valid token
        } else {
            // Token is expired or does not exist, generate a new one
            return generateDeveloperToken();
        }
    }


    private String generateDeveloperToken() throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Decode the Base64-encoded private key
        byte[] keyBytes = Base64.getDecoder().decode(appleMusicPrivateKey);
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        ECPrivateKey privateKey = (ECPrivateKey) keyFactory.generatePrivate(privateKeySpec);

        // Set expiration date for the token (6 months)
        Instant now = Instant.now();
        ZonedDateTime expirationDate = ZonedDateTime.now(ZoneId.of("UTC")).plusMonths(6);


        // Create JWT token
        String jwt = Jwts.builder()
                .setHeaderParam("kid", appleMusicKeyId)
                .claim("origin", origin)
                .setIssuer(appleMusicTeamId)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expirationDate.toInstant()))
                .signWith(privateKey, SignatureAlgorithm.ES256)
                .compact();

        tokenRepository.deleteAll();

        AppleMusicDeveloperToken appleMusicToken = new AppleMusicDeveloperToken();
        appleMusicToken.setToken(jwt);
        appleMusicToken.setCreatedAt(now);
        appleMusicToken.setExpiresAt(expirationDate.toInstant());
        tokenRepository.save(appleMusicToken);

        return jwt;
    }
}
