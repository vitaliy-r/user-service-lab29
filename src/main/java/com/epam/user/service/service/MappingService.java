package com.epam.user.service.service;

import com.epam.user.service.dto.UserDto;
import com.epam.user.service.model.User;

public interface MappingService {

  UserDto mapUserToUserDto(User user);

  User mapUserDtoToUser(UserDto userDto);

  User populateUserWithPresentUserDtoFields(User user, UserDto userDto);
}
