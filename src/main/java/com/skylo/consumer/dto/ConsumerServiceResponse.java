package com.skylo.consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsumerServiceResponse<T> {
  private Boolean success;
  private String message;
  private ErrorResponseDTO error;
  private T data;
}
