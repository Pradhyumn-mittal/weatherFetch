package com.java.weatherfetch.entity.pojo.outbound.googleMaps;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Location {
  private double lat;
  private double lng;

}
