package com.java.weatherfetch.outbound.impl;

import com.java.weatherfetch.configuration.GoogleMapsConfiguration;
import com.java.weatherfetch.configuration.GoogleMapsEndpointService;
import com.java.weatherfetch.entity.constant.enums.ResponseCode;
import com.java.weatherfetch.entity.pojo.outbound.googleMaps.Location;
import com.java.weatherfetch.entity.pojo.outbound.googleMaps.LocationResponse;
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
  public LocationResponse findLocation(Integer pincode)  {
    Response<LocationResponse> response;
    try {
      response = googleMapsEndpointService.findLocation(pincode,
          googleMapsConfiguration.getApi_key()).execute();
    }
    catch (IOException e)
    {
      LOGGER.error("findLocation unable to hit google api error {},pincode:{} ",e.getMessage(),pincode);
      throw new RuntimeException(ResponseCode.API_CALL_ERROR.getMessage());
      }
      LocationResponse locationResponse=response.body();
    if (!response.isSuccessful() || Objects.isNull(locationResponse)) {
        LOGGER.error("findLocation  Third party error pincode:{},statusCode:{} error:{}",pincode,response.code(),response.errorBody());
    }
    else if(!"OK".equalsIgnoreCase(Objects.requireNonNull(locationResponse.getStatus())))
    {
      LOGGER.error("findLocation third party error pincode:{},response:{}",pincode,locationResponse.getStatus());
      throw new RuntimeException(String.format("%s from googleMaps api",locationResponse.getStatus()));

    }
    return locationResponse;
    }
}
