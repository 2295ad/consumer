package com.skylo.consumer.service;

import java.util.Map;

public interface ConsumerService {

  void processTransaction(Map<String, Object> payload);
}
