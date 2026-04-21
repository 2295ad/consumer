package com.skylo.consumer.service;

import com.skylo.consumer.dto.TransactionDTO;
import java.util.Map;

public interface ConsumerService {

  void processTransaction(Map<String, Object> payload);

  TransactionDTO fetchTx(String txId);
}
