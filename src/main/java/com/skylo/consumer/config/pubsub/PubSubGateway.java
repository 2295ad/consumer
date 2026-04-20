package com.skylo.consumer.config.pubsub;

import com.skylo.consumer.dto.PubSubRequest;
import com.skylo.consumer.dto.constants.PubSubChannels;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway
public interface PubSubGateway {

  @Gateway(requestChannel = PubSubChannels.X_TO_CONSUMER_SERVICE)
  void sendMessageToNotificationService(Message<PubSubRequest> message);
}
