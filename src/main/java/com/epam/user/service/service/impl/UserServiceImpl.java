package com.epam.user.service.service.impl;

import com.epam.user.service.dto.UserDto;
import com.epam.user.service.model.User;
import com.epam.user.service.repository.UserRepository;
import com.epam.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public UserDto getUser(String email) {
    log.info("getUser by email {}", email);
    User user = userRepository.getUser(email);
    return mapUserToUserDto(user);
  }

  @Override
  public UserDto createUser(UserDto userDto) {
    log.info("createUser with email {}", userDto.getEmail());
    User user = mapUserDtoToUser(userDto);
    user = userRepository.createUser(user);
    return mapUserToUserDto(user);
  }

  @Override
  public UserDto updateUser(String email, UserDto userDto) {
    log.info("updateUser with email {}", email);
    User user = mapUserDtoToUser(userDto);

    User oldUser = userRepository.getUser(email);
    user.setEmail(oldUser.getEmail());
    user.setPassword(oldUser.getPassword());

    user = userRepository.updateUser(email, user);
    return mapUserToUserDto(user);
  }

  @Override
  public void deleteUser(String email) {
    log.info("deleteUser with email {}", email);
    userRepository.deleteUser(email);
  }

  private UserDto mapUserToUserDto(User user) {
    return UserDto.builder()
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .build();
  }

  private User mapUserDtoToUser(UserDto userDto) {
    return User.builder()
        .firstName(userDto.getFirstName())
        .lastName(userDto.getLastName())
        .email(userDto.getEmail())
        .password(userDto.getPassword())
        .build();
  }

}
