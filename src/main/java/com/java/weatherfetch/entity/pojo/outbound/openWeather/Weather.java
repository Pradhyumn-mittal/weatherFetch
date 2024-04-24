package com.java.weatherfetch.entity.pojo.outbound.openWeather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public  class Weather {
  private String main;
  private String description;
}

