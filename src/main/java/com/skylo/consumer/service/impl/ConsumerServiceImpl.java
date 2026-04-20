package com.skylo.consumer.service.impl;

import com.skylo.consumer.service.ConsumerService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

  @Override
  public void processTransaction(Map<String, Object> payload) {
    // insert to db
    // call api
  }
}
