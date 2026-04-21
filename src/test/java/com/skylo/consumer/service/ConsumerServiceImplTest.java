package com.skylo.consumer.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.skylo.consumer.dto.TransactionDTO;
import com.skylo.consumer.dto.TransactionStatusRequest;
import com.skylo.consumer.dto.enums.TransactionStatus;
import com.skylo.consumer.persistence.entity.TransactionEntity;
import com.skylo.consumer.persistence.entity.mapper.TransactionEntityMapper;
import com.skylo.consumer.service.impl.ConsumerServiceImpl;
import com.skylo.consumer.service.transactions.ConsumerTransactions;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ConsumerServiceImplTest {

  @Mock private ConsumerTransactions consumerTransactions;
  @Mock private TransactionEntityMapper mapper;

  @InjectMocks private ConsumerServiceImpl service;

  @Test
  void processTransaction_shouldSave() {
    Map<String, Object> payload = new HashMap<>();
    payload.put("transaction", "tx1");

    service.processTransaction(payload);

    verify(consumerTransactions).save(any());
  }

  @Test
  void fetchTx_shouldReturnDto() {
    TransactionEntity entity = new TransactionEntity();
    TransactionDTO dto = new TransactionDTO();

    when(consumerTransactions.fetchTx("tx1")).thenReturn(entity);
    when(mapper.toDto(entity)).thenReturn(dto);

    TransactionDTO result = service.fetchTx("tx1");

    assertNotNull(result);
  }

  @Test
  void updateTxStatus_shouldUpdate() {
    TransactionStatusRequest req = new TransactionStatusRequest();
    req.setTransaction("tx1");
    req.setStatus(TransactionStatus.SUCCESS);

    TransactionEntity entity = new TransactionEntity();

    when(consumerTransactions.fetchTx("tx1")).thenReturn(entity);
    when(mapper.toDto(entity)).thenReturn(new TransactionDTO());

    service.updateTxStatus(req);

    assertEquals(TransactionStatus.SUCCESS, entity.getStatus());
    verify(consumerTransactions).save(entity);
  }

  @Test
  void updateTxStatus_shouldThrowIfNotFound() {
    TransactionStatusRequest req = new TransactionStatusRequest();
    req.setTransaction("tx1");

    when(consumerTransactions.fetchTx("tx1")).thenReturn(null);

    assertThrows(Exception.class, () -> service.updateTxStatus(req));
  }
}
