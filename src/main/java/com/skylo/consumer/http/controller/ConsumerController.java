package com.skylo.consumer.http.controller;

import static com.skylo.consumer.dto.constants.ServiceConstants.SUCCESS;

import com.skylo.consumer.dto.ConsumerServiceResponse;
import com.skylo.consumer.dto.TransactionDTO;
import com.skylo.consumer.dto.TransactionStatusRequest;
import com.skylo.consumer.service.ConsumerService;
import com.skylo.consumer.utils.ResponseUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/consumer")
@RequiredArgsConstructor
public class ConsumerController {

  private final ConsumerService consumerService;

  @GetMapping(value = "/v1/{transaction}")
  public ConsumerServiceResponse<TransactionDTO> fetchTransaction(
      @PathVariable @NotBlank String transaction) {
    TransactionDTO txDto = consumerService.fetchTx(transaction);
    return ResponseUtil.success(SUCCESS, txDto);
  }

  @PutMapping(value = "/v1")
  public ConsumerServiceResponse<TransactionDTO> updateTransactionStatus(
      @RequestBody @Valid TransactionStatusRequest transactionStatusRequest) {
    TransactionDTO txDto = consumerService.updateTxStatus(transactionStatusRequest);
    return ResponseUtil.success(SUCCESS, txDto);
  }
}
