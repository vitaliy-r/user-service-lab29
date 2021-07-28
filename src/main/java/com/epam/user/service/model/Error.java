package com.epam.user.service.model;

import com.epam.user.service.model.enums.ErrorType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {

  private String message;
  private ErrorType errorType;
  private LocalDateTime timeStamp;

}
