package com.skylo.consumer.persistence.repository;

import com.skylo.consumer.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
  TransactionEntity findByTransactionId(String transactionId);
}
