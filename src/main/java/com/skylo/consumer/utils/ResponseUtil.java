package com.skylo.consumer.utils;

import com.skylo.consumer.dto.ConsumerServiceResponse;
import com.skylo.consumer.dto.ErrorResponseDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseUtil {
  public <T> ConsumerServiceResponse<T> success(String message, T data) {
    return new ConsumerServiceResponse<>(Boolean.TRUE, message, null, data);
  }

  public <T> ConsumerServiceResponse<T> failure(String message, ErrorResponseDTO error) {
    return new ConsumerServiceResponse<>(Boolean.FALSE, message, error, null);
  }
}
