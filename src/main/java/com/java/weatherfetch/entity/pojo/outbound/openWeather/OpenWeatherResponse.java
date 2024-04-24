package com.java.weatherfetch.entity.pojo.outbound.openWeather;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpenWeatherResponse {

  private Double lat;
  private Double lon;
  private List<WeatherData> data;
}
