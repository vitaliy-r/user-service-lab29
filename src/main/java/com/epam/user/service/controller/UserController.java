package com.epam.user.service.controller;

import com.epam.user.service.controller.assembler.UserAssembler;
import com.epam.user.service.controller.model.UserModel;
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
  private final UserAssembler userAssembler;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/users/{email}")
  public UserModel getUser(@PathVariable String email) {
    UserDto outUserDto = userService.getUser(email);
    return userAssembler.toModel(outUserDto);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/users")
  public UserModel createUser(@RequestBody @Validated(OnCreate.class) UserDto userDto) {
    UserDto outUserDto = userService.createUser(userDto);
    return userAssembler.toModel(outUserDto);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping(value = "/users/{email}")
  public UserModel updateUser(@PathVariable String email,
      @RequestBody @Validated(OnUpdate.class) UserDto userDto) {
    UserDto outUserDto = userService.updateUser(email, userDto);
    return userAssembler.toModel(outUserDto);
  }

  @DeleteMapping(value = "/users/{email}")
  public ResponseEntity<Void> deleteUser(@PathVariable String email) {
    userService.deleteUser(email);
    return ResponseEntity.noContent().build();
  }

}
