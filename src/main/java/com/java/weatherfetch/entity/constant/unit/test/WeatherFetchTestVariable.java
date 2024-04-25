package com.java.weatherfetch.entity.constant.unit.test;

import com.java.weatherfetch.entity.dao.PincodeDetail;
import com.java.weatherfetch.entity.dao.WeatherDetail;
import com.java.weatherfetch.entity.pojo.outbound.googleMaps.Location;
import com.java.weatherfetch.entity.pojo.outbound.googleMaps.LocationResponse;
import com.java.weatherfetch.entity.pojo.outbound.googleMaps.LocationResponse.Geometry;
import com.java.weatherfetch.entity.pojo.outbound.googleMaps.LocationResponse.Result;
import com.java.weatherfetch.entity.pojo.outbound.openWeather.OpenWeatherResponse;
import com.java.weatherfetch.entity.pojo.outbound.openWeather.Weather;
import com.java.weatherfetch.entity.pojo.outbound.openWeather.WeatherData;
import com.java.weatherfetch.entity.pojo.response.WeatherResponse;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class WeatherFetchTestVariable {
  public static final Integer PINCODE=110023;
  public static final String DATE="01-01-2000";
  public static final String PINCODE_PARAM="pincode";
  public static final String DATE_PARAM="date";
  public static final Date DATE_NOW=new Date();
  public static final Long SUNRISE_TIME=new Date().getTime();
  public static final Long SUNSET_TIME=new Date().getTime();
  public static final Long DATE_LONG=new Date().getTime();
  public static final String TEMPERATURE="10";
  public static final String FEELS_LIKE="9";
  public static final Integer PRESSURE=1;
  public static final Integer HUMIDITY=2;
  public static final Integer VISIBILITY=3;
  public static final Integer WIND_DEG=4;
  public static final Float WIND_SPEED=2.0f;
  public static final List<Weather> WEATHER = Collections.singletonList(Weather.builder().description("weather").main("WEATHER").build());

  public static final WeatherResponse WEATHER_RESPONSE_DB=WeatherResponse.builder().pincode(PINCODE).build();
  public static final WeatherDetail WEATHER_DETAIL_DB=WeatherDetail.builder().pincode(PINCODE).build();
  public static final PincodeDetail PINCODE_DETAIL=PincodeDetail.builder().pincode(PINCODE).location(
      Location.builder().lat(111.0).lng(111.0).build()).build();

  public static final List<WeatherData> WEATHER_DATA=Collections.singletonList(WeatherData.builder()
          .weather(WEATHER)
          .temp(TEMPERATURE)
          .dt(DATE_LONG)
          .sunset(SUNSET_TIME)
          .visibility(VISIBILITY)
          .sunrise(SUNRISE_TIME)
          .feels_like(FEELS_LIKE)
          .wind_speed(WIND_SPEED)
          .wind_deg(WIND_DEG)
          .pressure(PRESSURE)
          .humidity(HUMIDITY)
      .build());
  public static final OpenWeatherResponse OPEN_WEATHER_RESPONSE=OpenWeatherResponse.builder()
      .lon(111.0)
      .lat(111.0)
      .data(WEATHER_DATA)
      .build();
  public static final WeatherResponse WEATHER_RESPONSE_FINAL = WeatherResponse.builder()
      .wind_speed(WIND_SPEED)
      .wind_deg(WIND_DEG)
      .weatherInfo(WEATHER)
      .visibility(VISIBILITY)
      .temperature(TEMPERATURE)
      .sunsetTime(Date.from(Instant.ofEpochSecond(SUNSET_TIME)))
      .sunriseTime(Date.from(Instant.ofEpochSecond(SUNRISE_TIME)))
      .date(Date.from(Instant.ofEpochSecond(DATE_LONG)))
      .pincode(PINCODE)
      .pressure(PRESSURE)
      .location(Location.builder().lat(OPEN_WEATHER_RESPONSE.getLat()).lng(OPEN_WEATHER_RESPONSE.getLon()).build())
      .humidity(HUMIDITY)
      .feelsLike(FEELS_LIKE)
      .build();
  public static final Result[] RESULTS=new Result[]{Result.builder().geometry(Geometry.builder().location(Location.builder().lat(111.0).lng(111.0).build()).build()).build()};
public static final LocationResponse LOCATION_RESPONSE=LocationResponse.builder()
    .status("OK")
    .results(RESULTS).build();
}
