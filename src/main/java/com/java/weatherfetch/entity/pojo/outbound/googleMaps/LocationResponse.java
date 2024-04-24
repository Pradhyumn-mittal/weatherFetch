package com.java.weatherfetch.entity.pojo.outbound.googleMaps;

import com.google.gson.annotations.SerializedName;

public class LocationResponse {
  @SerializedName("results")
  private Result[] results;
  @SerializedName("status")
  private String status;

  public Result[] getResults() {
    return results;
  }

  public void setResults(
      Result[] results) {
    this.results = results;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public static class Result {
    @SerializedName("geometry")
    private Geometry geometry;

    public Geometry getGeometry() {
      return geometry;
    }
  }

  public static class Geometry {
    @SerializedName("location")
    private Location location;

    public Location getLocation() {
      return location;
    }
  }

}

