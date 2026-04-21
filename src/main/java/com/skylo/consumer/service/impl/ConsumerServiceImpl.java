package com.skylo.consumer.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skylo.consumer.dto.TransactionDTO;
import com.skylo.consumer.dto.TransactionStatusRequest;
import com.skylo.consumer.dto.enums.TransactionStatus;
import com.skylo.consumer.exception.ConsumerServiceException;
import com.skylo.consumer.http.rest.OrchestratorApi;
import com.skylo.consumer.persistence.entity.TransactionEntity;
import com.skylo.consumer.persistence.entity.mapper.TransactionEntityMapper;
import com.skylo.consumer.service.ConsumerService;
import com.skylo.consumer.service.transactions.ConsumerTransactions;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

  private final ConsumerTransactions consumerTransactions;
  private final ObjectMapper objectMapper;
  private final OrchestratorApi orchestratorApi;
  private final TransactionEntityMapper transactionEntityMapper;

  @Override
  @Transactional
  public void processTransaction(Map<String, Object> payload) {
    try {
      TransactionEntity transactionEntity =
          TransactionEntity.builder()
              .transactionId((String) payload.get("transaction"))
              .status(TransactionStatus.PENDING)
              .payload(payload)
              .build();
      consumerTransactions.save(transactionEntity);
      log.info("invoke orchestrator api");
      //      orchestratorApi.invokeOrchestrator(payload);
    } catch (Exception ex) {
      log.info("exception while processing tx - {}", payload.get("transaction"));
      throw new ConsumerServiceException(ex.getMessage());
    }
  }

  @Override
  public TransactionDTO fetchTx(String txId) {
    TransactionEntity transactionEntity = consumerTransactions.fetchTx(txId);
    return transactionEntityMapper.toDto(transactionEntity);
  }

  @Override
  public TransactionDTO updateTxStatus(TransactionStatusRequest transactionStatusRequest) {
    TransactionEntity transactionEntity =
        consumerTransactions.fetchTx(transactionStatusRequest.getTransaction());
    if (Objects.isNull(transactionEntity)) {
      log.info("Invalid tx id - " + transactionStatusRequest.getTransaction());
      throw new ConsumerServiceException(
          "Invalid tx id - " + transactionStatusRequest.getTransaction(), HttpStatus.BAD_REQUEST);
    }
    transactionEntity.setStatus(transactionStatusRequest.getStatus());
    consumerTransactions.save(transactionEntity);
    return transactionEntityMapper.toDto(transactionEntity);
  }
}
