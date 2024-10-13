package com.example.musicstore.repositories;

import com.example.musicstore.entities.AppleMusicDeveloperToken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AppleMusicDevTokenRepository extends JpaRepository<AppleMusicDeveloperToken, Long> {
}
