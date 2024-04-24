package com.java.weatherfetch.configuration;


import com.java.weatherfetch.entity.pojo.outbound.GoogleRouteResponse;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface GoogleMapsEndpointService {

  @POST("/maps/api/directions/json")
  Call<GoogleRouteResponse> findRoutes(@Query("origin") String origin,
      @Query("destination") String destination, @Query("key") String key,
      @Query("alternatives") Boolean alternatives);

}
