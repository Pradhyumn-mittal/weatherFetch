package com.java.weatherfetch.outbound.api;


import com.java.weatherfetch.entity.pojo.outbound.googleMaps.LocationResponse;
import java.io.IOException;

public interface GoogleMapsOutboundService {
    LocationResponse findLocation(Integer pincode) throws IOException;
    
    
}
