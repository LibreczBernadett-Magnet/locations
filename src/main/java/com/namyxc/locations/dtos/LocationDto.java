package com.namyxc.locations.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {

    private Long id;
    private String name;
    private double lat;
    private double lon;
}
