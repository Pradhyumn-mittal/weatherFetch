package com.java.weatherfetch.entity.constant.unit.test;

import com.java.weatherfetch.entity.pojo.outbound.googleMaps.Location;
import com.java.weatherfetch.entity.pojo.outbound.openWeather.OpenWeatherResponse;
import com.java.weatherfetch.entity.pojo.outbound.openWeather.Weather;
import com.java.weatherfetch.entity.pojo.outbound.openWeather.WeatherData;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class OpenWeatherApiTestVariable {
public static final String API_KEY="123";
  public static final String TEMPERATURE="10";

public static final Location LOCATION=Location.builder()
    .lng(111.0)
    .lat(111.0).build();

public static final OpenWeatherResponse OPEN_WEATHER_RESPONSE=OpenWeatherResponse.builder()
    .lon(111.0)
    .lat(111.0)
    .data(Collections.singletonList(WeatherData.builder().dt(new Date().getTime()).temp(TEMPERATURE).build())).build();
}
