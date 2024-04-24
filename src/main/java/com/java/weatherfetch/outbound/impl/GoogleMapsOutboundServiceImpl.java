package com.java.weatherfetch.outbound.impl;

import com.java.weatherfetch.configuration.GoogleMapsConfiguration;
import com.java.weatherfetch.configuration.GoogleMapsEndpointService;
import com.java.weatherfetch.entity.constant.enums.ResponseCode;
import com.java.weatherfetch.entity.pojo.outbound.GoogleRouteResponse;
import com.java.weatherfetch.outbound.api.GoogleMapsOutboundService;
import java.io.IOException;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

@Service
public class GoogleMapsOutboundServiceImpl implements GoogleMapsOutboundService {
  public static final Logger LOGGER= LoggerFactory.getLogger(GoogleMapsOutboundServiceImpl.class);
  @Autowired
  private GoogleMapsConfiguration googleMapsConfiguration;
  @Autowired
  private GoogleMapsEndpointService googleMapsEndpointService;

  @Override
  public GoogleRouteResponse findRouteInfo(String origin, String destination)  {
    Response<GoogleRouteResponse> response;
    try {
      response = googleMapsEndpointService.findRoutes(origin, destination,
          googleMapsConfiguration.getApi_key(), false).execute();
    }
    catch (IOException e)
    {
      LOGGER.error("findRouteInfo unable to hit google api error {},origin:{} destination:{}",e.getMessage(),origin,destination);
      throw new RuntimeException(ResponseCode.API_CALL_ERROR.getMessage());
      }
      GoogleRouteResponse googleRouteResponse=response.body();
    if (!response.isSuccessful() || Objects.isNull(googleRouteResponse)) {
        LOGGER.error("findRouteInfo  Third party error origin:{}, destination:{} statusCode:{} error:{}",origin,destination,response.code(),response.errorBody());
    }
    else if(!"OK".equalsIgnoreCase(Objects.requireNonNull(googleRouteResponse.getStatus())))
    {
      LOGGER.error("findRouteInfo third party error origin:{}, destination:{} response:{}",origin,destination,googleRouteResponse.getStatus());
      throw new RuntimeException(String.format("%s from googleMaps api",googleRouteResponse.getStatus()));

    }
    return googleRouteResponse;
    }
}
