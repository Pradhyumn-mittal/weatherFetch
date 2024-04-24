package com.java.weatherfetch.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
@ConditionalOnClass(Retrofit.class)
public class RetrofitConfiguration {
  @Bean
  public Retrofit retrofitGoogleMapsApi(GoogleMapsConfiguration googleMapsConfiguration) {
    return new Retrofit.Builder()
        .baseUrl(googleMapsConfiguration.getBase_url())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  @Bean
  public GoogleMapsEndpointService googleMapsEndpointService(Retrofit retrofitGoogleMapsApi) {
    return retrofitGoogleMapsApi.create(GoogleMapsEndpointService.class);
  }
  @Bean
  public Retrofit retrofitOpenWeatherApi(OpenWeatherConfiguration openWeatherConfiguration) {
    return new Retrofit.Builder()
        .baseUrl(openWeatherConfiguration.getBase_url())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  @Bean
  public OpenWeatherEndpointService OpenWeatherEndpointService(Retrofit retrofitOpenWeatherApi) {
    return retrofitOpenWeatherApi.create(OpenWeatherEndpointService.class);
  }

}
