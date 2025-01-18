package com.namyxc.locations;

import com.namyxc.locations.dtos.Location;
import com.namyxc.locations.dtos.LocationDto;
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
    private final LocationsDao locationsDao;

    public List<LocationDto> getLocations(Optional<String> name){
        return locationsDao.findAll().stream()
                .map(locationMapper::toDto)
                .toList();
    }

    public LocationDto getLocation(long id) {
        return locationMapper.toDto(locationsDao.getLocation(id));
    }

    public LocationDto createLocation(CreateLocationCommand command) {
        String name = command.getName();
        if (locationProperties.isTouppercase()){
            name = name.toUpperCase();
        }
        Location location = new Location(name, command.getLat(), command.getLon());
        Location created = locationsDao.createLocation(location);
        log.info("Created new location: {}", location.getId());
        return locationMapper.toDto(created);
    }

    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
        String name = command.getName();
        if (locationProperties.isTouppercase()){
            name = name.toUpperCase();
        }
        Location location = new Location(name, command.getLat(), command.getLon());
        Location updated = locationsDao.updateLocation(id, location);
        log.info("Updated location: {}", location.getId());
        return locationMapper.toDto(updated);
    }

    public void deleteLocation(long id) {
        locationsDao.delete(id);
        log.info("Deleted location: {}", id);
    }

    public void deleteAllLocation() {
        locationsDao.deleteAll();
    }

    private Supplier<LocationNotFoundException> notFoundExeption(long id) {
        return () -> new LocationNotFoundException("No location found with id: " + id);
    }
}
