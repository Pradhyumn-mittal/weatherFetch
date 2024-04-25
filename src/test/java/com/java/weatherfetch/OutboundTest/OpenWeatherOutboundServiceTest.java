package com.java.weatherfetch.OutboundTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.java.weatherfetch.configuration.OpenWeatherConfiguration;
import com.java.weatherfetch.configuration.OpenWeatherEndpointService;
import com.java.weatherfetch.entity.constant.unit.test.OpenWeatherApiTestVariable;
import com.java.weatherfetch.entity.pojo.outbound.openWeather.OpenWeatherResponse;
import com.java.weatherfetch.outbound.impl.OpenWeatherOutboundServiceImpl;
import java.io.IOException;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import retrofit2.Call;
import retrofit2.Response;

public class OpenWeatherOutboundServiceTest extends OpenWeatherApiTestVariable {
  @InjectMocks
  OpenWeatherOutboundServiceImpl openWeatherOutboundService;
  @Mock
  OpenWeatherConfiguration openWeatherConfiguration;

  @Mock
  OpenWeatherEndpointService openWeatherEndpointService;

  @Before
  public void setup()
  {
    initMocks(this);
  }
  @After
  public void teardown()
  {
    verify(openWeatherConfiguration).getApi_key();
    verify(openWeatherEndpointService).getWeather(any(),any(),any(),anyString());
    Mockito.verifyNoMoreInteractions(openWeatherConfiguration,openWeatherEndpointService);
  }

  @Test
  public void getWeatherDataSuccessTest() throws IOException {

    when(openWeatherConfiguration.getApi_key()).thenReturn(API_KEY);

    Call<OpenWeatherResponse> callMock = mock(Call.class);
    when(callMock.execute()).thenReturn(Response.success(OPEN_WEATHER_RESPONSE));

    when(openWeatherEndpointService.getWeather(any(),any(),any(),anyString()))
        .thenReturn(callMock);

    openWeatherOutboundService.getWeatherData(LOCATION,new Date());
  }
  @Test(expected = RuntimeException.class)
  public void getWeatherDataRuntimeError() throws IOException {

    when(openWeatherConfiguration.getApi_key()).thenReturn(API_KEY);

    Call<OpenWeatherResponse> callMock = mock(Call.class);
    when(callMock.execute()).thenThrow(new IOException());

    when(openWeatherEndpointService.getWeather(any(),any(),any(),anyString()))
        .thenReturn(callMock);

    openWeatherOutboundService.getWeatherData(LOCATION,new Date());
  }

}
