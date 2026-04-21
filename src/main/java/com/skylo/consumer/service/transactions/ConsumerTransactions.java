package com.skylo.consumer.service.transactions;

import com.skylo.consumer.persistence.entity.TransactionEntity;
import com.skylo.consumer.persistence.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConsumerTransactions {

  private final TransactionRepository transactionRepository;

  @Transactional
  public void save(TransactionEntity transactionEntity) {
    transactionRepository.save(transactionEntity);
  }

  @Transactional(readOnly = true)
  public TransactionEntity fetchTx(String transactionId) {
    return transactionRepository.findByTransactionId(transactionId);
  }
}
