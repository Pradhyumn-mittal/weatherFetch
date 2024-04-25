package com.java.weatherfetch.ServiceTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.java.weatherfetch.entity.constant.CacheKey;
import com.java.weatherfetch.entity.constant.unit.test.WeatherFetchTestVariable;
import com.java.weatherfetch.entity.pojo.response.WeatherResponse;
import com.java.weatherfetch.outbound.api.GoogleMapsOutboundService;
import com.java.weatherfetch.outbound.api.OpenWeatherOutboundService;
import com.java.weatherfetch.repository.PincodeDetailRepository;
import com.java.weatherfetch.repository.WeatherDetailRepository;
import com.java.weatherfetch.service.api.CacheService;
import com.java.weatherfetch.service.impl.WeatherFetchServiceImpl;
import java.io.IOException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

public class WeatherFetchServiceTest extends WeatherFetchTestVariable {

  @InjectMocks
  private WeatherFetchServiceImpl weatherFetchService;

  @Mock
  private PincodeDetailRepository pincodeDetailRepository;

  @Mock
  private CacheService cacheService;

  @Mock
  private WeatherDetailRepository weatherDetailRepository;
  @Mock
  private GoogleMapsOutboundService googleMapsOutboundService;
  @Mock
  private OpenWeatherOutboundService openWeatherOutboundService;
  @Before
  public void setUp() {
    initMocks(this);
    ReflectionTestUtils.setField(this.weatherFetchService,"cacheExpiry",100L);
  }
  @After
  public void teardown()
  {
    verifyNoMoreInteractions(this.pincodeDetailRepository,cacheService,weatherDetailRepository,googleMapsOutboundService,openWeatherOutboundService);
  }

  @Test
  public void getWeatherInfoTestCached() throws IOException {
    String cacheKey=CacheKey.WEATHER_RESPONSE+PINCODE+"-"+DATE_NOW;
    when(cacheService.findCacheByKey(cacheKey, WeatherResponse.class)).thenReturn(new WeatherResponse());

    WeatherResponse weatherResponse=weatherFetchService.getWeatherInfo(PINCODE,DATE_NOW);

    Assert.assertEquals(new WeatherResponse(),weatherResponse);

    verify(cacheService).findCacheByKey(cacheKey,WeatherResponse.class);
  }

  @Test
  public void getWeatherInfoDB() throws IOException{
    String cacheKey=CacheKey.WEATHER_RESPONSE+PINCODE+"-"+DATE_NOW;
    when(cacheService.findCacheByKey(cacheKey, WeatherResponse.class)).thenReturn(null);
    when(weatherDetailRepository.findWeatherDetailByPincodeAndDate(PINCODE,DATE_NOW)).thenReturn(WEATHER_DETAIL_DB);
    when(cacheService.createCache(cacheKey,WEATHER_RESPONSE_DB,100L)).thenReturn(true);
    WeatherResponse weatherResponse=weatherFetchService.getWeatherInfo(PINCODE,DATE_NOW);

    Assert.assertEquals(WEATHER_RESPONSE_DB,weatherResponse);

    verify(cacheService).findCacheByKey(cacheKey,WeatherResponse.class);
    verify(weatherDetailRepository).findWeatherDetailByPincodeAndDate(PINCODE,DATE_NOW);
    verify(cacheService).createCache(cacheKey,WEATHER_RESPONSE_DB,100L);
  }
  @Test
  public void getWeatherInfoPincodeDB() throws IOException{
    String cacheKey=CacheKey.WEATHER_RESPONSE+PINCODE+"-"+DATE_NOW;
    when(cacheService.findCacheByKey(cacheKey, WeatherResponse.class)).thenReturn(null);
    when(weatherDetailRepository.findWeatherDetailByPincodeAndDate(PINCODE,DATE_NOW)).thenReturn(null);
    when(pincodeDetailRepository.findPincodeDetailByPincode(PINCODE)).thenReturn(PINCODE_DETAIL);
    when(openWeatherOutboundService.getWeatherData(PINCODE_DETAIL.getLocation(),DATE_NOW)).thenReturn(OPEN_WEATHER_RESPONSE);
    when(cacheService.createCache(cacheKey,WEATHER_RESPONSE_FINAL,100L)).thenReturn(true);
    when(weatherDetailRepository.save(any())).thenReturn(WEATHER_DETAIL_DB);
    WeatherResponse weatherResponse=weatherFetchService.getWeatherInfo(PINCODE,DATE_NOW);


    Assert.assertEquals(WEATHER_RESPONSE_FINAL,weatherResponse);

    verify(cacheService).findCacheByKey(cacheKey,WeatherResponse.class);
    verify(weatherDetailRepository).findWeatherDetailByPincodeAndDate(PINCODE,DATE_NOW);
    verify(openWeatherOutboundService).getWeatherData(PINCODE_DETAIL.getLocation(),DATE_NOW);
    verify(pincodeDetailRepository).findPincodeDetailByPincode(PINCODE);
    verify(cacheService).createCache(cacheKey,WEATHER_RESPONSE_FINAL,100L);
    verify(weatherDetailRepository).save(any());
  }
  @Test
  public void getWeatherInfo_FreshEntry() throws IOException{
    String cacheKey=CacheKey.WEATHER_RESPONSE+PINCODE+"-"+DATE_NOW;
    when(cacheService.findCacheByKey(cacheKey, WeatherResponse.class)).thenReturn(null);
    when(weatherDetailRepository.findWeatherDetailByPincodeAndDate(PINCODE,DATE_NOW)).thenReturn(null);
    when(pincodeDetailRepository.findPincodeDetailByPincode(PINCODE)).thenReturn(null);
    when(googleMapsOutboundService.findLocation(PINCODE)).thenReturn(LOCATION_RESPONSE);
    when(openWeatherOutboundService.getWeatherData(PINCODE_DETAIL.getLocation(),DATE_NOW)).thenReturn(OPEN_WEATHER_RESPONSE);
    when(cacheService.createCache(cacheKey,WEATHER_RESPONSE_FINAL,100L)).thenReturn(true);
    when(weatherDetailRepository.save(any())).thenReturn(WEATHER_DETAIL_DB);
    when(pincodeDetailRepository.save(any())).thenReturn(PINCODE_DETAIL);
    WeatherResponse weatherResponse=weatherFetchService.getWeatherInfo(PINCODE,DATE_NOW);


    Assert.assertEquals(WEATHER_RESPONSE_FINAL,weatherResponse);

    verify(cacheService).findCacheByKey(cacheKey,WeatherResponse.class);
    verify(weatherDetailRepository).findWeatherDetailByPincodeAndDate(PINCODE,DATE_NOW);
    verify(openWeatherOutboundService).getWeatherData(PINCODE_DETAIL.getLocation(),DATE_NOW);
    verify(googleMapsOutboundService).findLocation(PINCODE);

    verify(pincodeDetailRepository).findPincodeDetailByPincode(PINCODE);
    verify(pincodeDetailRepository).save(any());
    verify(cacheService).createCache(cacheKey,WEATHER_RESPONSE_FINAL,100L);
    verify(weatherDetailRepository).save(any());
  }


}
