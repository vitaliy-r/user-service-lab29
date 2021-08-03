package com.epam.user.service.api;

import com.epam.user.service.controller.model.UserModel;
import com.epam.user.service.dto.UserDto;
import com.epam.user.service.dto.group.OnCreate;
import com.epam.user.service.dto.group.OnUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Api(tags = "User management API")
@RequestMapping("/api/v1/users")
public interface UserApi {

  @ApiImplicitParams({
      @ApiImplicitParam(name = "email", paramType = "path", required = true, value = "User email"),
  })
  @ApiOperation("Get user")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/{email}")
  UserModel getUser(@PathVariable String email);

  @ApiOperation("Create user")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  UserModel createUser(@Validated(OnCreate.class) @RequestBody UserDto userDto);

  @ApiImplicitParams({
      @ApiImplicitParam(name = "email", paramType = "path", required = true, value = "User email"),
  })
  @ApiOperation("Update user")
  @ResponseStatus(HttpStatus.OK)
  @PatchMapping(value = "/{email}")
  UserModel updateUser(@PathVariable String email,
      @RequestBody @Validated(OnUpdate.class) UserDto userDto);

  @ApiImplicitParams({
      @ApiImplicitParam(name = "email", paramType = "path", required = true, value = "User email"),
  })
  @ApiOperation("Delete user")
  @DeleteMapping(value = "/{email}")
  ResponseEntity<Void> deleteUser(@PathVariable String email);

}
