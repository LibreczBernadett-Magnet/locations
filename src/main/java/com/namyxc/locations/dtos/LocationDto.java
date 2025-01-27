package com.namyxc.locations.dtos;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "location")
public class LocationDto {

    private Long id;
    private String name;
    private double lat;
    private double lon;
}
