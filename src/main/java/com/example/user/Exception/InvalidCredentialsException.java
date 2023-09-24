package com.example.user.Exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class InvalidCredentialsException extends RuntimeException {
  private String message;
  private HttpStatus status;

  public InvalidCredentialsException(String message) {
    super();
    this.setMessage(message);
    this.setStatus(HttpStatus.UNAUTHORIZED);
  }

  public InvalidCredentialsException(String message, HttpStatus status) {
    super();
    this.setMessage(message);
    this.setStatus(status);

  }

}
