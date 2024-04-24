package com.java.weatherfetch.configuration;


import com.java.weatherfetch.entity.pojo.outbound.openWeather.OpenWeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherEndpointService {

  @GET("/data/3.0/onecall/timemachine")
  Call<OpenWeatherResponse> getWeather(@Query("lat") Double lat, @Query("lon") Double lon,
      @Query("dt") Long dt, @Query("appId") String key);

}
