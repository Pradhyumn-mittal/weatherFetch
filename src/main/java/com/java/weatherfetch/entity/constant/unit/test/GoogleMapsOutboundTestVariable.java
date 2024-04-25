package com.java.weatherfetch.entity.constant.unit.test;


import com.java.weatherfetch.entity.pojo.outbound.googleMaps.LocationResponse;

public class GoogleMapsOutboundTestVariable {
  public static final Integer ORIGIN=110022;
  public static final String BASE_URL="https://maps.googleapis.com/";
  public static final String API_KEY="2323";


  public static final LocationResponse LOCATION_RESPONSE= LocationResponse.builder().status("OK").build();
  public static final LocationResponse FAILED_LOCATION_RESPONSE= LocationResponse.builder().status("ZERO_RESULTS").build();
}
