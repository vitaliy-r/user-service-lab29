package com.epam.user.service.exception;

import com.epam.user.service.model.enums.ErrorType;

public class UserAlreadyExistsException extends ServiceException {

  private static final String DEFAULT_MESSAGE = "User already exists!";

  public UserAlreadyExistsException() {
    super(DEFAULT_MESSAGE);
  }

  @Override
  public ErrorType getErrorType() {
    return ErrorType.VALIDATION_ERROR_TYPE;
  }

}
