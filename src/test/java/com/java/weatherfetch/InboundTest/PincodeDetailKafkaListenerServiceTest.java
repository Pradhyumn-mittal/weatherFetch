package com.java.weatherfetch.InboundTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.weatherfetch.entity.constant.unit.test.KafkaTestVariable;
import com.java.weatherfetch.inbound.impl.PincodeDetailKafkaListenerServiceImpl;
import com.java.weatherfetch.repository.PincodeDetailRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class PincodeDetailKafkaListenerServiceTest extends KafkaTestVariable {

  @InjectMocks
  private PincodeDetailKafkaListenerServiceImpl pincodeDetailKafkaListenerService;

  @Mock
  private PincodeDetailRepository pincodeDetailRepository;
  @Before
  public void setup(){

    initMocks(this);
  }
  @After
      public void teardown()
  {
    Mockito.verifyNoMoreInteractions(pincodeDetailRepository);
  }
  @Test
  public void listenerTest()
  {
    when(pincodeDetailRepository.save(PINCODE_DETAIL_ORIGIN)).thenReturn(PINCODE_DETAIL_ORIGIN);
    when(pincodeDetailRepository.save(PINCODE_DETAIL_DESTINATION)).thenReturn(PINCODE_DETAIL_DESTINATION);
    pincodeDetailKafkaListenerService.receivePincodeDetail(GENERATED_RECORD);
    verify(pincodeDetailRepository,times(2)).save(any());
  }

}
