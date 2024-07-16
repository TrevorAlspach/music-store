package com.example.musicstore;

import com.example.musicstore.entities.User;
import com.example.musicstore.repositories.UserRepository;
import com.example.musicstore.rest.dto.RegisterRequest;
import com.example.musicstore.services.AuthService;
import com.example.musicstore.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MusicStoreApplication {

	private static final Logger logger = LoggerFactory.getLogger(MusicStoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MusicStoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(AuthService authService, PasswordEncoder passwordEncoder){
		return (args -> {
			RegisterRequest registerRequest = new RegisterRequest();
			registerRequest.setEmail("trevoralspach@gmail.com");
			registerRequest.setUsername("trevoralspach@gmail.com");
			registerRequest.setRoles("ADMIN");
			registerRequest.setPassword("password");

			authService.signup(registerRequest);
			/*userRepository.findAll().forEach(user -> {
				logger.info(user.toString());
			});*/


		});
	}

}
