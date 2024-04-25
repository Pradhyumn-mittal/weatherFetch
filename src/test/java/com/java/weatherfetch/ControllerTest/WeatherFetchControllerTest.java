package com.java.weatherfetch.ControllerTest;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.java.weatherfetch.controller.WeatherFetchController;
import com.java.weatherfetch.entity.constant.ApiPath;
import com.java.weatherfetch.entity.constant.enums.ResponseCode;
import com.java.weatherfetch.entity.constant.unit.test.WeatherFetchTestVariable;
import com.java.weatherfetch.entity.pojo.response.WeatherResponse;
import com.java.weatherfetch.service.api.WeatherFetchService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

public class WeatherFetchControllerTest extends WeatherFetchTestVariable {
  @InjectMocks
  private WeatherFetchController weatherFetchController;
  @Mock
  private WeatherFetchService weatherFetchService;
  private MockMvc mockMvc;
  @Test
  public void runTimeExceptionTest() throws Exception {
    when(weatherFetchService.getWeatherInfo(eq(PINCODE),any())).thenReturn(new WeatherResponse());
    this.mockMvc.perform(
            get(ApiPath.WEATHER_FETCH)
                .param(PINCODE_PARAM, String.valueOf(PINCODE))
                .param(DATE_PARAM, (DATE)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code",equalTo(ResponseCode.SUCCESS.getCode())));

    verify(this.weatherFetchService).getWeatherInfo(eq(PINCODE),any());
  }
  @Before
  public void setUp() {
    initMocks(this);

    this.mockMvc = standaloneSetup(this.weatherFetchController).build();
  }
  @After
  public void teardown()
  {
    verifyNoMoreInteractions(this.weatherFetchService);
  }
}
