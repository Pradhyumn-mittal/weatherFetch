package com.java.weatherfetch.OutboundTest;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.java.weatherfetch.configuration.GoogleMapsConfiguration;
import com.java.weatherfetch.configuration.GoogleMapsEndpointService;
import com.java.weatherfetch.entity.constant.unit.test.GoogleMapsOutboundTestVariable;
import com.java.weatherfetch.entity.pojo.outbound.googleMaps.LocationResponse;
import com.java.weatherfetch.outbound.impl.GoogleMapsOutboundServiceImpl;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import retrofit2.Call;
import retrofit2.Response;

public class GoogleMapsOutboundServiceTest extends GoogleMapsOutboundTestVariable {
  @InjectMocks
  GoogleMapsOutboundServiceImpl googleMapsOutboundService;
  @Mock
  GoogleMapsEndpointService googleMapsEndpointService;

  @Mock
  GoogleMapsConfiguration googleMapsConfiguration;

  @Before
  public void setUp() {
    initMocks(this);
  }
  @Test
  public void findLocationSuccess() throws IOException {

    when(googleMapsConfiguration.getBase_url()).thenReturn(BASE_URL);
    when(googleMapsConfiguration.getApi_key()).thenReturn(API_KEY);

    Call<LocationResponse> callMock = mock(Call.class);
    when(callMock.execute()).thenReturn(Response.success(LOCATION_RESPONSE));

    when(googleMapsEndpointService.findLocation(anyInt(),anyString()))
        .thenReturn(callMock);

    LocationResponse result = googleMapsOutboundService.findLocation(ORIGIN);

    Assert.assertEquals(LOCATION_RESPONSE, result);

    verify(googleMapsConfiguration).getApi_key();
    verify(googleMapsEndpointService).findLocation(anyInt(),anyString());
  }

  @Test(expected = RuntimeException.class)
  public void findLocation_IOException() throws IOException {
    when(googleMapsConfiguration.getBase_url()).thenReturn(BASE_URL);
    when(googleMapsConfiguration.getApi_key()).thenReturn(API_KEY);

    Call<LocationResponse> callMock = mock(Call.class);
    when(callMock.execute()).thenThrow(new IOException());

    when(googleMapsEndpointService.findLocation(anyInt(),anyString()))
        .thenReturn(callMock);

    googleMapsOutboundService.findLocation(ORIGIN);


    verify(googleMapsConfiguration).getApi_key();
    verify(googleMapsEndpointService).findLocation(anyInt(),anyString());
  }

  @Test(expected = RuntimeException.class)
  public void testFindLocation_NonOKStatus() throws IOException {
    when(googleMapsConfiguration.getBase_url()).thenReturn(BASE_URL);
    when(googleMapsConfiguration.getApi_key()).thenReturn(API_KEY);

    Call<LocationResponse> callMock = mock(Call.class);
    when(callMock.execute()).thenReturn(Response.success(FAILED_LOCATION_RESPONSE));

    when(googleMapsEndpointService.findLocation(anyInt(),anyString()))
        .thenReturn(callMock);

    googleMapsOutboundService.findLocation(ORIGIN);

    verify(googleMapsConfiguration).getApi_key();
    verify(googleMapsEndpointService).findLocation(anyInt(),anyString());
  }
}
