package com.java.weatherfetch.entity.pojo.outbound.openWeather;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherData {
    private Long dt;
    private Long sunrise;
    private Long sunset;
    private String temp;
    private String feels_like;
    private Integer pressure;
    private Integer humidity;
    private Integer visibility;
    private Float wind_speed;
    private Integer wind_deg;
    private List<Weather> weather;



}
