package com.skylo.consumer.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.skylo.consumer.dto.enums.TransactionStatus;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TransactionEntity implements Serializable {
  @Id
  @Column(name = "id", nullable = false, unique = true)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "transaction_id")
  private String transactionId;

  @Column(name = "payload")
  private String payload;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private TransactionStatus status;

  @Version
  @Column(name = "version")
  private Integer version;

  @Column(name = "created_at", insertable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", insertable = false, updatable = false)
  private LocalDateTime updatedAt;
}
