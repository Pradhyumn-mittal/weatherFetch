package com.java.weatherfetch.outbound.impl;

import com.java.weatherfetch.configuration.OpenWeatherConfiguration;
import com.java.weatherfetch.configuration.OpenWeatherEndpointService;
import com.java.weatherfetch.entity.constant.enums.ResponseCode;
import com.java.weatherfetch.entity.pojo.outbound.googleMaps.Location;
import com.java.weatherfetch.entity.pojo.outbound.googleMaps.LocationResponse;
import com.java.weatherfetch.entity.pojo.outbound.openWeather.OpenWeatherResponse;
import com.java.weatherfetch.entity.pojo.response.WeatherResponse;
import com.java.weatherfetch.outbound.api.OpenWeatherOutboundService;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

@Service
public class OpenWeatherOutboundServiceImpl implements OpenWeatherOutboundService {
  public static final Logger LOGGER= LoggerFactory.getLogger(OpenWeatherOutboundServiceImpl.class);
  @Autowired
  private OpenWeatherConfiguration openWeatherConfiguration;
  @Autowired
  private OpenWeatherEndpointService openWeatherEndpointService;
  @Override
  public OpenWeatherResponse getWeatherData(Location location, Date date) {
    Response<OpenWeatherResponse> response;
    Long epochDate=date.getTime()/1000;
    try {
      response = openWeatherEndpointService.getWeather(location.getLat(), location.getLng(),
          epochDate, openWeatherConfiguration.getApi_key()).execute();
    } catch (IOException e) {
      LOGGER.error("findLocation unable to hit openWeather api error {},location:{} date:{} ", e.getMessage(),
          location,date);
      throw new RuntimeException(ResponseCode.API_CALL_ERROR.getMessage());
    }
    OpenWeatherResponse openWeatherResponse=response.body();
    if (!response.isSuccessful() || Objects.isNull(openWeatherResponse)) {
      LOGGER.error("findLocation  Third party error location:{},statusCode:{} error:{}",location,response.code(),response.errorBody());
    }
    return openWeatherResponse;
  }
}
