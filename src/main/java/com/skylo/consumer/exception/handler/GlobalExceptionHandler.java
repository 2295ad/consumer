package com.skylo.consumer.exception.handler;

import com.skylo.consumer.dto.ConsumerServiceResponse;
import com.skylo.consumer.dto.ErrorResponseDTO;
import com.skylo.consumer.exception.ConsumerServiceException;
import com.skylo.consumer.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ConsumerServiceException.class)
  public ResponseEntity<ConsumerServiceResponse<Object>> handlePaymentServiceException(
      ConsumerServiceException ex, WebRequest request) {
    ErrorResponseDTO error =
        ErrorResponseDTO.builder().code(ex.getHttpStatus()).message(ex.getMessage()).build();
    ConsumerServiceResponse<Object> response = ResponseUtil.failure(ex.getMessage(), error);
    return ResponseEntity.status(ex.getHttpStatus()).body(response);
  }
}
