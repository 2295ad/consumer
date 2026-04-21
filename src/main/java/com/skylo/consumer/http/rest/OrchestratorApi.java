package com.skylo.consumer.http.rest;

import com.skylo.consumer.config.ExternalApi;
import com.skylo.consumer.exception.ConsumerServiceException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrchestratorApi {

  @Value("${external-service.orchestrator.base-url}")
  private String orchestratorBaseUrl;

  private final RestTemplate restTemplate;
  private final ExternalApi externalApi;

  private HttpHeaders getHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return headers;
  }

  private <T, R> R httpCall(
      String url, HttpMethod method, T requestBody, ParameterizedTypeReference<R> responseType) {

    HttpEntity<T> entity = new HttpEntity<>(requestBody, getHeaders());
    ResponseEntity<R> resp = restTemplate.exchange(url, method, entity, responseType);
    return resp.getBody();
  }

  public void invokeOrchestrator(Map<String, Object> payload) {
    String url =
        orchestratorBaseUrl.concat(
            externalApi.getServices().get("orchestrator").get("initiate-workflow"));
    log.info(" base url {} reg url {} ", orchestratorBaseUrl, url);
    Map<String, Object> response =
        this.httpCall(
            url,
            HttpMethod.POST,
            payload,
            new ParameterizedTypeReference<Map<String, Object>>() {});
    if (!(Boolean) response.get("success")) {
      throw new ConsumerServiceException("unable to invoke orchestrator");
    }
  }
}
