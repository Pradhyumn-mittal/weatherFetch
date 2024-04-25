package com.java.weatherfetch.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Getter
@Setter
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "weather.fetch.google.maps")
public class GoogleMapsConfiguration {
  private String base_url;
  private String api_key;


}
