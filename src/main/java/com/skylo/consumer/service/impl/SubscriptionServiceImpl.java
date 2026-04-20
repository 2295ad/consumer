package com.skylo.consumer.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import com.skylo.consumer.service.ConsumerService;
import com.skylo.consumer.service.SubscriptionService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
  private final ObjectMapper objectMapper;
  private final ConsumerService consumerService;

  @Override
  @ServiceActivator(inputChannel = "xToConsumerServiceSyncChannel")
  public void handleMessage(Message<Object> message) {
    log.info("Received message from X_TO_CONSUMER_SERVICE: {}", message);
    Object payload = message.getPayload();
    try {
      BasicAcknowledgeablePubsubMessage basicAcknowledgeablePubsubMessage =
          message
              .getHeaders()
              .get(GcpPubSubHeaders.ORIGINAL_MESSAGE, BasicAcknowledgeablePubsubMessage.class);
      Map<String, Object> payloadMap = objectMapper.convertValue(payload, Map.class);
      processMessage(payloadMap);
      log.info("ack sent");
      basicAcknowledgeablePubsubMessage.ack();
    } catch (Exception e) {
      log.error("Error processing message for consumer service subscriber: {}", e.getMessage(), e);
    }
  }

  @Override
  public void processMessage(Map<String, Object> payload) {
    log.info("Processing message: {}", payload);
    Map<String, Object> data = (Map<String, Object>) payload.get("data");
    consumerService.processTransaction(data);
  }
}
