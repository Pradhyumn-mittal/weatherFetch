package com.java.weatherfetch.repository;


import com.java.weatherfetch.entity.dao.PincodeDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PincodeDetailRepository extends MongoRepository<PincodeDetail,String>{
  PincodeDetail findPincodeDetailByPincode(Integer pinocode);
}
