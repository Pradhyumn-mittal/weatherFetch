package com.java.weatherfetch.repository;

import com.java.weatherfetch.entity.dao.WeatherDetail;
import java.util.Date;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeatherDetailRepository extends MongoRepository<WeatherDetail,String> {

  WeatherDetail findWeatherDetailByPincodeAndDate(Integer pincode, Date date);

}
