package com.skylo.consumer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.skylo.consumer.dto.enums.TransactionStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionStatusRequest {
  @NotNull private TransactionStatus status;
  @NotBlank private String transaction;
}
