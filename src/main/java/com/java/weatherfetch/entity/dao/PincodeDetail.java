package com.java.weatherfetch.entity.dao;


import com.java.weatherfetch.entity.constant.CollectionName;
import com.java.weatherfetch.entity.constant.fields.PincodeDetailFields;
import com.java.weatherfetch.entity.pojo.outbound.googleMaps.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = CollectionName.PINCODE_DETAIL)
public class PincodeDetail extends BaseMongo {

  @Field(value = PincodeDetailFields.PINCODE)
  private Integer pincode;
  @Field(value = PincodeDetailFields.LOCATION)
  private Location location;

}
