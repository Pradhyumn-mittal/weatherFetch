package com.java.weatherfetch.entity.pojo.outbound;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleRouteResponse {
  private List<Route> routes;
  private String status;
}
