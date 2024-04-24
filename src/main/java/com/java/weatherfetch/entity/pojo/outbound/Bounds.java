package com.java.weatherfetch.entity.pojo.outbound;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Bounds {
    private Location northeast;
    private Location southwest;

}
