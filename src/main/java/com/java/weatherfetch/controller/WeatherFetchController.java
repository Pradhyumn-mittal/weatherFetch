package com.java.weatherfetch.controller;


import com.java.weatherfetch.entity.constant.ApiPath;
import com.java.weatherfetch.entity.constant.enums.ResponseCode;
import com.java.weatherfetch.entity.pojo.response.BaseResponse;
import com.java.weatherfetch.service.api.WeatherFetchService;
import java.io.IOException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(ApiPath.WEATHER_FETCH)
public class WeatherFetchController {

  @Autowired
  private WeatherFetchService weatherFetchService;


  @GetMapping
  public BaseResponse<Boolean> getWeatherData(
      @RequestParam Integer origin,
      @RequestParam Date date
  ) {

    return BaseResponse.constructResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),null,
        true);
  }

}