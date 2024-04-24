package com.java.weatherfetch.entity.pojo.response;

import com.java.weatherfetch.entity.pojo.outbound.googleMaps.Location;
import com.java.weatherfetch.entity.pojo.outbound.openWeather.Weather;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor

public class WeatherResponse {
  private Integer pincode;
  private Date date;
  private Location location;
  private String temperature;
  private Date sunriseTime;
  private Date sunsetTime;
  private String feelsLike;
  private List<Weather> weatherInfo;
  private Integer pressure;
  private Integer humidity;
  private Integer visibility;
  private Float wind_speed;
  private Integer wind_deg;


}
