package com.epam.user.service.dto;

import com.epam.user.service.dto.group.OnCreate;
import com.epam.user.service.dto.group.OnUpdate;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class UserDto {

  @NotBlank(message = "'firstName' shouldn't be empty", groups = OnCreate.class)
  private String firstName;

  @NotBlank(message = "'lastName' shouldn't be empty", groups = OnCreate.class)
  private String lastName;

  @Email
  @Null(message = "'email' should be absent in request", groups = OnUpdate.class)
  @NotBlank(message = "'email' shouldn't be empty", groups = OnCreate.class)
  private String email;

  @Null(message = "'password' should be absent in request", groups = OnUpdate.class)
  @NotBlank(message = "'password' shouldn't be empty", groups = OnCreate.class)
  private String password;

  @Null(message = "'repeatPassword' should be absent in request", groups = OnUpdate.class)
  @NotBlank(message = "'repeatPassword' shouldn't be empty!", groups = OnCreate.class)
  private String repeatPassword;

}
