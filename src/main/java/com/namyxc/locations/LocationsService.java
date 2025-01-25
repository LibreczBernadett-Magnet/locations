package com.namyxc.locations;

import com.namyxc.locations.dtos.Location;
import com.namyxc.locations.dtos.LocationDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@EnableConfigurationProperties(LocationProperties.class)
@AllArgsConstructor
@Slf4j
public class LocationsService {

    private LocationProperties locationProperties;
    private final LocationMapper locationMapper;
    private final LocationsRepository repository;
    private final EventstoreGateway eventstoreGateway;

    public List<LocationDto> getLocations(Optional<String> name){
        return repository.findAll().stream()
                .map(locationMapper::toDto)
                .toList();
    }

    public LocationDto getLocation(long id) {
        return locationMapper.toDto(repository.findById(id).orElseThrow(notFoundExeption(id)));
    }

    public LocationDto createLocation(CreateLocationCommand command) {
        String name = command.getName();
        if (locationProperties.isTouppercase()){
            name = name.toUpperCase();
        }
        Location location = new Location(name, command.getLat(), command.getLon());
        Location created = repository.save(location);
        log.info("Created new location: {}", location.getId());
        eventstoreGateway.sendEvent(new CreateEventCommand("Created new location: " + location.getId()));
        return locationMapper.toDto(created);
    }

    @Transactional
    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
        String name = command.getName();
        if (locationProperties.isTouppercase()){
            name = name.toUpperCase();
        }
        Location location = repository.findById(id).orElseThrow(notFoundExeption(id));
        location.setName(name);
        location.setLat(command.getLat());
        location.setLon(command.getLon());
        Location updated = repository.save(location);
        log.info("Updated location: {}", location.getId());
        eventstoreGateway.sendEvent(new CreateEventCommand("Updated location: " + location.getId()));
        return locationMapper.toDto(updated);
    }

    public void deleteLocation(long id) {
        repository.deleteById(id);
        log.info("Deleted location: {}", id);
    }

    public void deleteAllLocation() {
        repository.deleteAll();
    }

    private Supplier<LocationNotFoundException> notFoundExeption(long id) {
        return () -> new LocationNotFoundException("No location found with id: " + id);
    }
}
