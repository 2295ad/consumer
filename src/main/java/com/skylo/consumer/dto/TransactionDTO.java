package com.skylo.consumer.dto;

import com.skylo.consumer.dto.enums.TransactionStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO {
  private Integer id;

  private String transactionId;

  private String payload;

  private TransactionStatus status;

  private Integer version;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
