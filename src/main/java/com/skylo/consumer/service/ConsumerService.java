package com.skylo.consumer.service;

import com.skylo.consumer.dto.TransactionDTO;
import com.skylo.consumer.dto.TransactionStatusRequest;
import java.util.Map;

public interface ConsumerService {

  void processTransaction(Map<String, Object> payload);

  TransactionDTO fetchTx(String txId);

  TransactionDTO updateTxStatus(TransactionStatusRequest transactionStatusRequest);
}
