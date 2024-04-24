package com.java.weatherfetch.entity.dao;

import com.java.weatherfetch.entity.constant.CollectionName;
import com.java.weatherfetch.entity.constant.fields.WeatherDetailFields;
import com.java.weatherfetch.entity.pojo.outbound.googleMaps.Location;
import com.java.weatherfetch.entity.pojo.outbound.openWeather.Weather;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = CollectionName.WEATHER_DETAIL)
public class WeatherDetail {
  @Field(value = WeatherDetailFields.PINCODE)
  private Integer pincode;
  @Field(value = WeatherDetailFields.DATE)
  private Date date;
  @Field(value = WeatherDetailFields.LOCATION)
  private Location location;
  @Field(value = WeatherDetailFields.TEMPERATURE)
  private String temperature ;
  @Field(value = WeatherDetailFields.SUNRISE_DATE)
  private Date sunriseDate;
  @Field(value = WeatherDetailFields.SUNSET_DATE)
  private Date sunsetDate;
  @Field(value = WeatherDetailFields.FEELS_LIKE)
  private String feelsLike;
  @Field(value = WeatherDetailFields.WEATHER_INFO)
  private List<Weather> weatherInfo;
  @Field(value = WeatherDetailFields.PRESSURE)
  private Integer pressure;
  @Field(value = WeatherDetailFields.HUMIDITY)
  private Integer humidity;
  @Field(value = WeatherDetailFields.VISIBILITY)
  private Integer visibility;
  @Field(value = WeatherDetailFields.WIND_DEGREE)
  private Integer wind_degree;
  @Field(value = WeatherDetailFields.WIND_SPEED)
  private Float wind_speed;


}
