package com.namyxc.locations;

import com.namyxc.locations.dtos.LocationDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationsController {

    private final LocationsService locationsService;

    public LocationsController(LocationsService locationsService){
        this.locationsService = locationsService;
    }

    @GetMapping("/locations")
    public List<LocationDto> getLocations(){
        return locationsService.getLocations();
    }
}
