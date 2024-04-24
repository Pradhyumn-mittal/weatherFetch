package com.java.weatherfetch.inbound.api;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface PincodeDetailKafkaListenerService {
  void receivePincodeDetail(ConsumerRecord<String,String> record);

}
