package com.epam.user.service.service.impl;

import com.epam.user.service.dto.UserDto;
import com.epam.user.service.exception.UserAlreadyExistsException;
import com.epam.user.service.exception.UserNotFoundException;
import com.epam.user.service.model.User;
import com.epam.user.service.repository.UserRepository;
import com.epam.user.service.service.MappingService;
import com.epam.user.service.service.UserService;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final MappingService mappingService;

  @Override
  public UserDto getUser(String email) {
    log.info("Finding user by {} email...", email);
    User user = userRepository.findByEmail(email)
        .orElseThrow(UserNotFoundException::new);
    log.info("User with {} email is found", email);
    return mappingService.mapUserToUserDto(user);
  }

  @Override
  @Transactional
  public UserDto createUser(UserDto userDto) {
    log.info("Creating user with {} email...", userDto.getEmail());
    if (userRepository.existsByEmail(userDto.getEmail())) {
      throw new UserAlreadyExistsException();
    }
    User user = mappingService.mapUserDtoToUser(userDto);
    user = userRepository.save(user);
    log.info("Used with {} email, {} id successfully created", user.getEmail(), user.getId());
    return mappingService.mapUserToUserDto(user);
  }

  @Override
  @Transactional
  public UserDto updateUser(String email, UserDto userDto) {
    log.info("Updating user with {} email...", email);
    User persistedUser = userRepository.findByEmail(email)
        .orElseThrow(UserNotFoundException::new);
    persistedUser = mappingService.populateUserWithPresentUserDtoFields(persistedUser, userDto);
    User storedUser = userRepository.save(persistedUser);
    log.info("User with {} email successfully updated", storedUser.getEmail());
    return mappingService.mapUserToUserDto(persistedUser);
  }

  @Override
  @Transactional
  public void deleteUser(String email) {
    log.info("deleteUser with email {}", email);
    User user = userRepository.findByEmail(email)
        .orElseThrow(UserNotFoundException::new);
    userRepository.delete(user);
    log.info("Used with {} email successfully deleted", email);
  }

}
