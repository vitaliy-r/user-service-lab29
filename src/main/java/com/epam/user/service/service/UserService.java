package com.epam.user.service.service;

import com.epam.user.service.dto.UserDto;

public interface UserService {

  UserDto getUser(String email);

  UserDto createUser(UserDto userDto);

  UserDto updateUser(String email, UserDto userDto);

  void deleteUser(String email);

}
