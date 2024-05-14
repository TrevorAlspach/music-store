package com.example.musicstore.repositories;

import com.example.musicstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {

    public Optional<User> findByEmail(String emailId);

    public Optional<User> findByUsername(String userName);
}
