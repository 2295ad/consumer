package com.skylo.consumer.config.pubsub;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.skylo.consumer.dto.enums.TopicName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

@Configuration
@Slf4j
public class PubSubAdaptorConfiguration {

  @Value("${spring.cloud.gcp.project-id}")
  private String gcpTopic;

  @Value("${spring.cloud.gcp.pubsub.x-to-consumer-service}")
  private String xToConsumerService;

  @Bean
  public PubSubInboundChannelAdapter xInputChannelAdaptor(
      MessageChannel xToConsumerServiceSyncChannel, PubSubTemplate pubSubTemplate) {
    PubSubInboundChannelAdapter adapter =
        new PubSubInboundChannelAdapter(
            pubSubTemplate,
            String.format(
                TopicName.X_TO_CONSUMER_SERVICE.getSubscriptionName(),
                gcpTopic,
                xToConsumerService));
    adapter.setOutputChannel(xToConsumerServiceSyncChannel);
    adapter.setAckMode(AckMode.MANUAL);
    adapter.setPayloadType(Object.class);
    return adapter;
  }
}
