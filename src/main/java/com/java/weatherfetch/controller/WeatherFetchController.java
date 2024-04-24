package com.java.weatherfetch.controller;


import com.java.weatherfetch.entity.constant.ApiPath;
import com.java.weatherfetch.entity.constant.enums.ResponseCode;
import com.java.weatherfetch.entity.pojo.response.BaseResponse;
import com.java.weatherfetch.entity.pojo.response.WeatherResponse;
import com.java.weatherfetch.service.api.WeatherFetchService;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
  public BaseResponse<WeatherResponse> getWeatherData(
      @RequestParam Integer pincode,
      @RequestParam
      @DateTimeFormat(pattern = "dd-MM-yyyy")
      @Schema(description = "Format dd-MM-yyyy", example = "01-01-2000")
      Date date
  ) throws IOException {

    return BaseResponse.constructResponse(ResponseCode.SUCCESS.getCode(),
        ResponseCode.SUCCESS.getMessage(), null,
        weatherFetchService.getWeatherInfo(pincode, date));
  }

}