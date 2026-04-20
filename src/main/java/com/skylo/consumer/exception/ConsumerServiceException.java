package com.skylo.consumer.exception;

import org.springframework.http.HttpStatus;

public class ConsumerServiceException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  private final HttpStatus httpStatus;

  public ConsumerServiceException(String message, Throwable throwable, HttpStatus httpStatus) {
    super(message, throwable);
    this.httpStatus = httpStatus;
  }

  public ConsumerServiceException(String message, HttpStatus httpStatus, Throwable throwable) {
    super(message, throwable);
    this.httpStatus = httpStatus;
  }

  public ConsumerServiceException(String message, Throwable throwable) {
    super(message, throwable);
    this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
  }

  public ConsumerServiceException(String message) {
    super(message);
    this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
  }

  public ConsumerServiceException(String message, HttpStatus httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
  }

  public HttpStatus getHttpStatus() {
    return this.httpStatus;
  }
}
