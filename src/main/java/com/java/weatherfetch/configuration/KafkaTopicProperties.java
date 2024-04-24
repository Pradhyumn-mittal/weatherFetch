package com.java.weatherfetch.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "weather.fetch.kafka.topic")
public class KafkaTopicProperties {
  private String pincodeData;

  public String getPincodeData() {
    return pincodeData;
  }

  public void setPincodeData(String pincodeData) {
    this.pincodeData = pincodeData;
  }
}
