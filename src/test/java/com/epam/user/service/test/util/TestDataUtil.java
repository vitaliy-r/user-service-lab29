package com.epam.user.service.test.util;

import com.epam.user.service.dto.UserDto;
import com.epam.user.service.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDataUtil {

  public static final String FIRST_NAME = "FirstName";
  public static final String LAST_NAME = "LastName";
  public static final String TEST_EMAIL = "email@email.com";
  private static final String PASSWORD = "password";

  public static User createUser() {
    return User.builder()
        .firstName(FIRST_NAME)
        .lastName(LAST_NAME)
        .email(TEST_EMAIL)
        .password(PASSWORD)
        .build();
  }

  public static UserDto createUserDto() {
    return UserDto.builder()
        .firstName(FIRST_NAME)
        .lastName(LAST_NAME)
        .email(TEST_EMAIL)
        .password(PASSWORD)
        .repeatPassword(PASSWORD)
        .build();
  }

}
