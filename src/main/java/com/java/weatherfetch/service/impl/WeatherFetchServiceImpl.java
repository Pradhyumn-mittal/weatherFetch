package com.java.weatherfetch.service.impl;


import com.java.weatherfetch.entity.constant.CacheKey;
import com.java.weatherfetch.entity.dao.PincodeDetail;
import com.java.weatherfetch.entity.dao.WeatherDetail;
import com.java.weatherfetch.entity.pojo.outbound.googleMaps.Location;
import com.java.weatherfetch.entity.pojo.outbound.googleMaps.LocationResponse;
import com.java.weatherfetch.entity.pojo.outbound.openWeather.OpenWeatherResponse;
import com.java.weatherfetch.entity.pojo.outbound.openWeather.WeatherData;
import com.java.weatherfetch.entity.pojo.response.WeatherResponse;
import com.java.weatherfetch.outbound.api.GoogleMapsOutboundService;
import com.java.weatherfetch.outbound.api.OpenWeatherOutboundService;
import com.java.weatherfetch.repository.PincodeDetailRepository;
import com.java.weatherfetch.repository.WeatherDetailRepository;
import com.java.weatherfetch.service.api.CacheService;
import com.java.weatherfetch.service.api.WeatherFetchService;
import io.reactivex.rxjava3.core.Completable;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WeatherFetchServiceImpl implements WeatherFetchService {
  private static final Logger LOGGER = LoggerFactory.getLogger(WeatherFetchServiceImpl.class);
  @Autowired
  private PincodeDetailRepository pincodeDetailRepository;
  @Autowired
  private CacheService cacheService;
  @Autowired
  private GoogleMapsOutboundService googleMapsOutboundService;
  @Autowired
  private OpenWeatherOutboundService openWeatherOutboundService;
  @Autowired
  private WeatherDetailRepository weatherDetailRepository;
  @Value("${weather.fetch.cache.expiry}")
  private Long cacheExpiry;


  private Completable saveData(WeatherResponse response) {
    return Completable.create(completableEmitter -> {
      WeatherDetail weatherDetail = WeatherDetail.builder()
          .weatherInfo(response.getWeatherInfo())
          .feelsLike(response.getFeelsLike())
          .humidity(response.getHumidity())
          .date(response.getDate())
          .pressure(response.getPressure())
          .sunsetDate(response.getSunsetTime())
          .sunriseDate(response.getSunriseTime())
          .visibility(response.getVisibility())
          .temperature(response.getTemperature())
          .wind_speed(response.getWind_speed())
          .wind_degree(response.getWind_deg())
          .pincode(response.getPincode())
          .location(response.getLocation())
          .build();
       weatherDetailRepository.save(weatherDetail);
      completableEmitter.onComplete();
    });
  }

  private WeatherResponse mapWeatherDetail(WeatherDetail weatherDetail)
  {
    return WeatherResponse.builder()
        .date(weatherDetail.getDate())
        .feelsLike(weatherDetail.getFeelsLike())
        .humidity(weatherDetail.getHumidity())
        .location(weatherDetail.getLocation())
        .pincode(weatherDetail.getPincode())
        .pressure(weatherDetail.getPressure())
        .sunriseTime(weatherDetail.getSunriseDate())
        .sunsetTime(weatherDetail.getSunsetDate())
        .temperature(weatherDetail.getTemperature())
        .visibility(weatherDetail.getVisibility())
        .weatherInfo(weatherDetail.getWeatherInfo())
        .wind_deg(weatherDetail.getWind_degree())
        .wind_speed(weatherDetail.getWind_speed())
        .build();
  }


  @Override
  public WeatherResponse getWeatherInfo(Integer pincode, Date date) throws IOException {
    LOGGER.info("getWeatherInfo pincode:{} date:{}",pincode,date);
    String cacheKey= CacheKey.WEATHER_RESPONSE+pincode+"-"+date;
    WeatherResponse weatherResponse;
    weatherResponse=cacheService.findCacheByKey(cacheKey,WeatherResponse.class);
    if(Objects.nonNull(weatherResponse))
      return weatherResponse;
    WeatherDetail weatherDetail =weatherDetailRepository.findWeatherDetailByPincodeAndDate(pincode,date);
    if(Objects.nonNull(weatherDetail))
    {weatherResponse= mapWeatherDetail(weatherDetail);
      cacheService.createCache(cacheKey,weatherResponse,cacheExpiry);
      return weatherResponse;
    }
    PincodeDetail pincodeDetail=pincodeDetailRepository.findPincodeDetailByPincode(pincode);
    Location coordinates;
    if(pincodeDetail==null)
    {
      LocationResponse  locationResponse= googleMapsOutboundService.findLocation(pincode);
      coordinates=locationResponse.getResults()[0].getGeometry().getLocation();
      pincodeDetailRepository.save(PincodeDetail.builder().pincode(pincode).location(coordinates).build());
    }
    else{
      coordinates=pincodeDetail.getLocation();
        }
    OpenWeatherResponse openWeatherResponse=openWeatherOutboundService.getWeatherData(coordinates,date);
     weatherResponse = convertToWeatherResponse(pincode,openWeatherResponse);
    cacheService.createCache(cacheKey,weatherResponse,cacheExpiry);
     saveData(weatherResponse).subscribe();
    return weatherResponse;
  }
  private WeatherResponse convertToWeatherResponse(Integer pincode,OpenWeatherResponse openWeatherResponse)
  {
    WeatherData weatherData=openWeatherResponse.getData().get(0);

    return WeatherResponse.builder()
        .wind_speed(weatherData.getWind_speed())
        .wind_deg(weatherData.getWind_deg())
        .weatherInfo(weatherData.getWeather())
        .visibility(weatherData.getVisibility())
        .temperature(weatherData.getTemp())
        .sunsetTime(Date.from(Instant.ofEpochSecond(weatherData.getSunset())))
        .sunriseTime(Date.from(Instant.ofEpochSecond(weatherData.getSunrise())))
        .date(Date.from(Instant.ofEpochSecond(weatherData.getDt())))
        .pincode(pincode)
        .pressure(weatherData.getPressure())
        .location(Location.builder().lat(openWeatherResponse.getLat()).lng(
            openWeatherResponse.getLon()).build())
        .humidity(weatherData.getHumidity())
        .feelsLike(weatherData.getFeels_like())
        .build();

        }
}
