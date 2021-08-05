package com.epam.user.service.exception;

import com.epam.user.service.model.enums.ErrorType;

public class PasswordsNotValidException extends ServiceException {

  private static final String DEFAULT_MESSAGE =
      "Passwords did not pass validation! Make sure they are not empty & equal.";

  public PasswordsNotValidException() {
    super(DEFAULT_MESSAGE);
  }

  @Override
  public ErrorType getErrorType() {
    return ErrorType.VALIDATION_ERROR_TYPE;
  }

}
