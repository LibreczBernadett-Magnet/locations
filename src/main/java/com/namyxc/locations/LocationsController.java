package com.namyxc.locations;

import com.namyxc.locations.dtos.LocationDto;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/locations")
    public LocationDto createLocation(@RequestBody CreateLocationCommand command){
        return locationsService.createLocation(command);
    }

    @PutMapping("/locations/{id}")
    public LocationDto updateLocation(@PathVariable("id") long id, @RequestBody UpdateLocationCommand command){
        return locationsService.updateLocation(id,command);
    }

    @DeleteMapping("/locations/{id}")
    public void deleteLocation(@PathVariable("id") long id){
        locationsService.deleteLocation(id);
    }
}
