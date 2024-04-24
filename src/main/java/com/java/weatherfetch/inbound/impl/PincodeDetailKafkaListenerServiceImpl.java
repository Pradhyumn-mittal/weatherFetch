package com.java.weatherfetch.inbound.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.weatherfetch.entity.dao.PincodeDetail;
import com.java.weatherfetch.entity.pojo.response.PincodeData;
import com.java.weatherfetch.inbound.api.PincodeDetailKafkaListenerService;
import com.java.weatherfetch.repository.PincodeDetailRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PincodeDetailKafkaListenerServiceImpl implements PincodeDetailKafkaListenerService {
  private static final Logger LOGGER = LoggerFactory.getLogger(PincodeDetailKafkaListenerServiceImpl.class);
  @Autowired
  private PincodeDetailRepository pincodeDetailRepository;
  @Override
  @KafkaListener(topics = "${weather.fetch.kafka.topic.pincode-data}")
  public void receivePincodeDetail(ConsumerRecord<String ,String> record)
  {
    LOGGER.info("receivePincodeDetail receive kafka: {}", record);
    ObjectMapper mapper = new ObjectMapper();
    PincodeData pincodeData = null;
    try {
      pincodeData= mapper.readValue(record.value(), PincodeData.class);
    }
    catch (JsonProcessingException e)
    {
      LOGGER.error("error parsing kafka message record:{}",record.value());
    }
    if(pincodeData!=null)
    {
      PincodeDetail pincodeDetailOrigin=PincodeDetail.builder()
          .pincode(pincodeData.getOriginPincode())
          .location(pincodeData.getOriginLocation()).build();
      pincodeDetailRepository.save(pincodeDetailOrigin);
      PincodeDetail pincodeDetailDestination=PincodeDetail.builder()
          .pincode(pincodeData.getDestinationPincode())
          .location(pincodeData.getDestinationLocation()).build();
      pincodeDetailRepository.save(pincodeDetailDestination);
    }
  }
}
