package com.java.weatherfetch.configuration;


import com.java.weatherfetch.entity.pojo.outbound.googleMaps.LocationResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface GoogleMapsEndpointService {

  @GET("/maps/api/geocode/json")
  Call<LocationResponse> findLocation(@Query("address") Integer address, @Query("key") String key);

}
