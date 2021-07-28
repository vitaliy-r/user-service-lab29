package com.epam.user.service.controller;

import com.epam.user.service.dto.UserDto;
import com.epam.user.service.dto.group.OnCreate;
import com.epam.user.service.dto.group.OnUpdate;
import com.epam.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/users/{email}")
  public UserDto getUser(@PathVariable String email) {
    return userService.getUser(email);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/users")
  public UserDto createUser(@RequestBody @Validated(OnCreate.class) UserDto userDto) {
    return userService.createUser(userDto);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping(value = "/users/{email}")
  public UserDto updateUser(@PathVariable String email,
      @RequestBody @Validated(OnUpdate.class) UserDto userDto) {
    return userService.updateUser(email, userDto);
  }

  @DeleteMapping(value = "/users/{email}")
  public ResponseEntity<Void> deleteUser(@PathVariable String email) {
    userService.deleteUser(email);
    return ResponseEntity.noContent().build();
  }

}
