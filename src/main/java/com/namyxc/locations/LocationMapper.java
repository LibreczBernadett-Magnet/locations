package com.namyxc.locations;

import com.namyxc.locations.dtos.Location;
import com.namyxc.locations.dtos.LocationDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationDto toDto(Location location);
    List<LocationDto> toDto(List<Location> locations);
}
