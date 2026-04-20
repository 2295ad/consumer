package com.skylo.consumer.service;

import java.util.Map;
import org.springframework.messaging.Message;

public interface SubscriptionService {
  void handleMessage(Message<Object> message);

  void processMessage(Map<String, Object> payload);
}
