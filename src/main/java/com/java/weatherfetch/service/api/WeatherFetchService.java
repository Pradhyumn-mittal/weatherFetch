package com.java.weatherfetch.service.api;

import com.java.weatherfetch.entity.pojo.response.WeatherResponse;
import java.io.IOException;
import java.util.Date;

public interface WeatherFetchService {
    WeatherResponse getWeatherInfo(Integer pincode, Date date) throws IOException;

}
