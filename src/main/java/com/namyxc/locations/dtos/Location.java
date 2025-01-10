package com.namyxc.locations.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Location {
    private long id;
    private String name;
    private double lat;
    private double lon;


}
