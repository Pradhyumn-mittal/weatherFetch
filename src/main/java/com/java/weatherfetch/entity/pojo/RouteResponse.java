package com.java.weatherfetch.entity.pojo;

import com.java.weatherfetch.entity.pojo.outbound.Location;
import com.java.weatherfetch.entity.pojo.outbound.OverviewPolyline;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RouteResponse implements Serializable {
  private String originPincode;
  private String destinationPincode;
  private String duration;
  private String distance;
  private List<DirectionSteps> steps;
  private Location originLocation;
  private Location destinationLocation;
  private OverviewPolyline polygonInfo;
}



