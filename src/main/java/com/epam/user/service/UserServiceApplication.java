package com.epam.user.service;

import com.epam.user.service.dto.UserDto;
import com.epam.user.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class UserServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserServiceApplication.class, args);
  }

  @Bean
  public CommandLineRunner demoUser(UserService userService,
      @Value("${app.user.password}") String password,
      @Value("${app.user.email}") String email) {
    return args -> {
      UserDto userDto = UserDto.builder()
          .firstName("TestUserF")
          .lastName("TestUserL")
          .email(email)
          .password(password)
          .repeatPassword(password)
					.build();
      log.info("Creating default user with email: {}", email);
      userService.createUser(userDto);
    };
  }

}
