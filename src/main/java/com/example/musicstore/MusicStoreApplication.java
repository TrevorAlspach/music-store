package com.example.musicstore;

import com.example.musicstore.entities.User;
import com.example.musicstore.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MusicStoreApplication {

	private static final Logger logger = LoggerFactory.getLogger(MusicStoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MusicStoreApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner demo(UserRepository userRepository){
		return (args -> {

			userRepository.findAll().forEach(user -> {
				logger.info(user.toString());
			});
		});
	}*/

}
