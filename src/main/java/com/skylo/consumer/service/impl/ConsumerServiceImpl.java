package com.skylo.consumer.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skylo.consumer.dto.TransactionDTO;
import com.skylo.consumer.dto.enums.TransactionStatus;
import com.skylo.consumer.exception.ConsumerServiceException;
import com.skylo.consumer.persistence.entity.TransactionEntity;
import com.skylo.consumer.service.ConsumerService;
import com.skylo.consumer.service.transactions.ConsumerTransactions;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

  private final ConsumerTransactions consumerTransactions;
  private final ObjectMapper objectMapper;

  @Override
  @Transactional
  public void processTransaction(Map<String, Object> payload) {
    try {
      TransactionEntity transactionEntity =
          TransactionEntity.builder()
              .transactionId((String) payload.get("transactionId"))
              .status(TransactionStatus.PENDING)
              .payload(objectMapper.writeValueAsString(payload))
              .build();
      consumerTransactions.save(transactionEntity);

    } catch (Exception ex) {
      log.info("exception while processing tx - {}", payload.get("transaction"));
      throw new ConsumerServiceException(ex.getMessage());
    }
  }

  @Override
  public TransactionDTO fetchTx(String txId) {}
}
