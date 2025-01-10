package com.namyxc.locations;

import com.namyxc.locations.dtos.LocationDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class LocationsController {

    private final LocationsService locationsService;

    public LocationsController(LocationsService locationsService){
        this.locationsService = locationsService;
    }

    @GetMapping("/locations")
    public List<LocationDto> getLocations(@RequestParam Optional<String> name){
        return locationsService.getLocations(name);
    }
    @GetMapping("/locations/{id}")
    public LocationDto getLocation(@PathVariable("id") long id){
        return  locationsService.getLocation(id);
    }
}
