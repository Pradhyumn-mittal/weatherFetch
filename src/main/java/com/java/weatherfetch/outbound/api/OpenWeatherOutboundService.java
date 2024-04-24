package com.java.weatherfetch.outbound.api;

import com.java.weatherfetch.entity.pojo.outbound.googleMaps.Location;
import com.java.weatherfetch.entity.pojo.outbound.openWeather.OpenWeatherResponse;
import com.java.weatherfetch.entity.pojo.response.WeatherResponse;
import java.util.Date;

public interface OpenWeatherOutboundService {

  OpenWeatherResponse getWeatherData(Location location, Date date);

}
