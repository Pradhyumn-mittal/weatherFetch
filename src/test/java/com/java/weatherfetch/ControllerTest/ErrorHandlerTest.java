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


import com.java.weatherfetch.controller.ErrorHandlerController;
import com.java.weatherfetch.controller.WeatherFetchController;
import com.java.weatherfetch.entity.constant.ApiPath;
import com.java.weatherfetch.entity.constant.enums.ResponseCode;
import com.java.weatherfetch.entity.constant.unit.test.ErrorHandlerTestVariable;
import com.java.weatherfetch.entity.pojo.exception.BusinessLogicException;
import com.java.weatherfetch.service.api.WeatherFetchService;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

public class ErrorHandlerTest extends ErrorHandlerTestVariable {

  @InjectMocks
  private WeatherFetchController weatherFetchController;
  @InjectMocks
  private ErrorHandlerController errorHandlerController;

  @Mock
  private WeatherFetchService weatherFetchService;

  private MockMvc mockMvc;


  @Test
  public void runTimeExceptionTest() throws Exception {
    when(weatherFetchService.getWeatherInfo(eq(PINCODE),any())).thenThrow(new RuntimeException(
        ResponseCode.PARSE_ERROR.getCode()));
    this.mockMvc.perform(
        get(ApiPath.WEATHER_FETCH)
            .param(PINCODE_PARAM, String.valueOf(PINCODE))
            .param(DATE_PARAM, (DATE)))
        .andExpect(status().is5xxServerError())
        .andExpect(jsonPath("$.code",equalTo(ResponseCode.RUNTIME_ERROR.getCode())));

    verify(this.weatherFetchService).getWeatherInfo(eq(PINCODE),any());
  }

  @Test
  public void exceptionTest() throws Exception {
    when(weatherFetchService.getWeatherInfo(eq(PINCODE),any())).thenThrow(new IOException(ResponseCode.SYSTEM_ERROR.getMessage()));
    this.mockMvc.perform(
            get(ApiPath.WEATHER_FETCH)
                .param(PINCODE_PARAM, String.valueOf(PINCODE))
                .param(DATE_PARAM, (DATE)))
        .andExpect(status().is5xxServerError())
        .andExpect(jsonPath("$.message",equalTo(ResponseCode.SYSTEM_ERROR.getMessage())));

    verify(this.weatherFetchService).getWeatherInfo(eq(PINCODE),any());
  }
  @Test
  public void bleExceptionTest() throws Exception {
    when(weatherFetchService.getWeatherInfo(eq(PINCODE),any())).thenThrow(new BusinessLogicException(ResponseCode.DATA_NOT_EXIST));
    this.mockMvc.perform(
            get(ApiPath.WEATHER_FETCH)
                .param(PINCODE_PARAM, String.valueOf(PINCODE))
                .param(DATE_PARAM, (DATE)))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.message",equalTo(ResponseCode.DATA_NOT_EXIST.getMessage())));

    verify(this.weatherFetchService).getWeatherInfo(eq(PINCODE),any());
  }

  @Before
  public void setUp() {
    initMocks(this);

    this.mockMvc = standaloneSetup(this.weatherFetchController)
        .setControllerAdvice(errorHandlerController).build();
  }
  @After
  public void teardown()
  {
    verifyNoMoreInteractions(this.weatherFetchService);
  }


}
