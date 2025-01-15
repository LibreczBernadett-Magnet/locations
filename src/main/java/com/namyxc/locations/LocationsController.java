package com.namyxc.locations;

import com.namyxc.locations.dtos.LocationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public ResponseEntity<LocationDto> getLocation(@PathVariable("id") long id){
        try {
            LocationDto locationDto = locationsService.getLocation(id);
            return ResponseEntity
                    .ok()
                    .header("Response-Id", UUID.randomUUID().toString())
                    .body(locationDto);
        }
        catch (LocationNotFoundException e){
            return ResponseEntity
                    .notFound()
                    .header("Response-Id", UUID.randomUUID().toString())
                    .build();
        }
    }

    @PostMapping("/locations")
    public ResponseEntity<LocationDto> createLocation(@RequestBody CreateLocationCommand command,
                                                      UriComponentsBuilder uri){
        LocationDto locationDto = locationsService.createLocation(command);
        return ResponseEntity
                .created(uri.path("/locations/{id}").buildAndExpand(locationDto.getId()).toUri())
                .body(locationDto);
    }

    @PutMapping("/locations/{id}")
    public ResponseEntity<LocationDto> updateLocation(@PathVariable("id") long id, @RequestBody UpdateLocationCommand command){
        try {
            LocationDto locationDto = locationsService.updateLocation(id, command);
            return ResponseEntity
                    .ok()
                    .header("Response-Id", UUID.randomUUID().toString())
                    .body(locationDto);
        }catch (LocationNotFoundException e){
            return ResponseEntity
                    .notFound()
                    .header("Response-Id", UUID.randomUUID().toString())
                    .build();
        }
    }

    @DeleteMapping("/locations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable("id") long id){
        locationsService.deleteLocation(id);
    }
}
