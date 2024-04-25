package com.java.weatherfetch.entity.constant.unit.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.weatherfetch.entity.dao.PincodeDetail;
import com.java.weatherfetch.entity.pojo.outbound.googleMaps.Location;
import com.java.weatherfetch.entity.pojo.response.PincodeData;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaTestVariable {
  public static final Logger LOGGER= LoggerFactory.getLogger(KafkaTestVariable.class);
  protected final ConsumerRecord<String,String> GENERATED_RECORD = generateRecord();
  public static final String KAFKA_TOPIC="topic";
  public static final Integer ORIGIN_PINCODE=110024;
  public static final Integer DESTINATION_PINCODE=110025;
  public static final Location ORIGIN_LOCATION=Location.builder().lat(111.0).lng(111.0).build();
  public static final Location DESTINATION_LOCATION=Location.builder().lat(111.0).lng(111.0).build();

  private ConsumerRecord<String, String> generateRecord() {
    TopicPartition topicPartition = new TopicPartition(KAFKA_TOPIC, 0);
    String sendKafkaMessage = null;
    ObjectMapper objectMapper=new ObjectMapper();

    try {
      sendKafkaMessage = objectMapper.writeValueAsString(PINCODE_DATA);
    } catch (Exception e) {
      LOGGER.error("Failed generate record", e);
    }

    return new ConsumerRecord<>(topicPartition.topic(), topicPartition.partition(), 0, "value",
        sendKafkaMessage);
  }
  public static final PincodeData PINCODE_DATA=PincodeData.builder()
      .destinationPincode(DESTINATION_PINCODE)
      .originPincode(ORIGIN_PINCODE)
      .originLocation(ORIGIN_LOCATION)
      .destinationLocation(DESTINATION_LOCATION)
      .build();
  public static final PincodeDetail PINCODE_DETAIL_ORIGIN=PincodeDetail.builder()
      .pincode(ORIGIN_PINCODE)
      .location(ORIGIN_LOCATION).build();
  public static final PincodeDetail PINCODE_DETAIL_DESTINATION=PincodeDetail.builder()
      .pincode(DESTINATION_PINCODE)
      .location(DESTINATION_LOCATION).build();
}
