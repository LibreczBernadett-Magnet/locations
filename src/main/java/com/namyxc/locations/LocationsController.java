package com.namyxc.locations;

import com.namyxc.locations.dtos.LocationDto;
import com.namyxc.locations.dtos.LocationsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
public class LocationsController {

    private final LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping(value = "/locations", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public LocationsDto getLocations(@RequestParam Optional<String> name) {
        return new LocationsDto(locationsService.getLocations(name));
    }

    @GetMapping(value = "/locations/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public LocationDto getLocation(@PathVariable("id") long id) {
        return locationsService.getLocation(id);

    }

    @PostMapping("/locations")
    public ResponseEntity<LocationDto> createLocation(@RequestBody CreateLocationCommand command,
                                                      UriComponentsBuilder uri) {
        LocationDto locationDto = locationsService.createLocation(command);
        return ResponseEntity
                .created(uri.path("/locations/{id}").buildAndExpand(locationDto.getId()).toUri())
                .body(locationDto);
    }

    @PutMapping("/locations/{id}")
    public LocationDto updateLocation(@PathVariable("id") long id, @RequestBody UpdateLocationCommand command) {
        return locationsService.updateLocation(id, command);
    }

    @DeleteMapping("/locations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable("id") long id) {
        locationsService.deleteLocation(id);
    }

}
