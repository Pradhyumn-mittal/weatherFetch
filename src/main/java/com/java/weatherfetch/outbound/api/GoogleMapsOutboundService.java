package com.java.weatherfetch.outbound.api;

import com.java.weatherfetch.entity.pojo.outbound.GoogleRouteResponse;
import java.io.IOException;

public interface GoogleMapsOutboundService {
    GoogleRouteResponse findRouteInfo(String origin,String destination) throws IOException;
    
    
}
