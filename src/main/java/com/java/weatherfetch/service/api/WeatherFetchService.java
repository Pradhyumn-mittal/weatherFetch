package com.java.weatherfetch.service.api;

import com.java.weatherfetch.entity.pojo.RouteResponse;
import java.io.IOException;

public interface WeatherFetchService {
    RouteResponse findRouteByPincode(Integer origin,Integer destination) throws IOException;

}
