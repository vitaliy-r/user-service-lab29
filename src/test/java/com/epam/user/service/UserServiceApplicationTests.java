package com.epam.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.epam.user.service.controller.model.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserServiceApplicationTests {

  @Value("http://localhost:${local.server.port}/api/v1/users/")  // SPeL
  private String baseUrl;

  @Value("${app.user.email}")
  private String email;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  void getUserTest() {
    UserModel userModel = testRestTemplate.getForObject(baseUrl + email, UserModel.class);
    assertEquals(userModel.getUserDto().getEmail(), email);
  }

}
