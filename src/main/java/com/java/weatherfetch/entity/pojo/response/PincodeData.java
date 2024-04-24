package com.java.weatherfetch.entity.pojo.response;


import com.java.weatherfetch.entity.pojo.outbound.googleMaps.Location;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PincodeData implements Serializable {

  private Integer originPincode;
  private Location originLocation;
  private Integer destinationPincode;
  private Location destinationLocation;
}
