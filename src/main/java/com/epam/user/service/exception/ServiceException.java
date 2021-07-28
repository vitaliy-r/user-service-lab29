package com.epam.user.service.exception;

import com.epam.user.service.model.enums.ErrorType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class ServiceException extends RuntimeException {

  private ErrorType errorType;

  public ServiceException(String message) {
    super(message);
  }

  public ErrorType getErrorType() {
    return ErrorType.FATAL_ERROR_TYPE;
  }

}
