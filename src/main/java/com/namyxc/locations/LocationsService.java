package com.namyxc.locations;

import com.namyxc.locations.dtos.Location;
import com.namyxc.locations.dtos.LocationDto;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

@Service
@EnableConfigurationProperties(LocationProperties.class)
@AllArgsConstructor
public class LocationsService {
//    private final ModelMapper modelMapper;

    private LocationProperties locationProperties;
    private final LocationMapper locationMapper;

    private final AtomicLong counter = new AtomicLong();

//    public LocationsService(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }

    private final List<Location> locations = Collections.synchronizedList(new ArrayList<>(List.of(
            new Location(counter.getAndIncrement(),"Location - 0", 0,0),
            new Location(counter.getAndIncrement(),"Location - 1", 1,-1),
            new Location(counter.getAndIncrement(),"Location - 2", 2,-2),
            new Location(counter.getAndIncrement(),"Location - 3", 3,-3),
            new Location(counter.getAndIncrement(),"Location - 4", 4,-4)
    )));

    public List<LocationDto> getLocations(Optional<String> name){
//        Type targetType = new TypeToken<List<LocationDto>>(){}.getType();
        List<Location> filtered = locations.stream()
                .filter(location -> name.isEmpty() || location.getName().toLowerCase().contains(name.get().toLowerCase()))
                .toList();
//        return modelMapper.map(filtered, targetType);
        return locationMapper.toDto(filtered);
    }

    public LocationDto getLocation(long id) {
//        return modelMapper.map(locations.stream()
        return locationMapper.toDto(locations.stream()
                .filter(l -> l.getId() ==  id).findAny()
                .orElseThrow(notFoundExeption(id)));
    }

    public LocationDto createLocation(CreateLocationCommand command) {
        String name = command.getName();
        if (locationProperties.isTouppercase()){
            name = name.toUpperCase();
        }
        Location location = new Location(counter.getAndIncrement(), name, command.getLat(), command.getLon());
        locations.add(location);
//        return modelMapper.map(location, LocationDto.class);
        return locationMapper.toDto(location);
    }

    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
        String name = command.getName();
        if (locationProperties.isTouppercase()){
            name = name.toUpperCase();
        }
        Location location = locations.stream()
                .filter(l -> l.getId() == id)
                .findFirst().orElseThrow(notFoundExeption(id));
        location.setName(name);
        location.setLat(command.getLat());
        location.setLon(command.getLon());

//        return modelMapper.map(location, LocationDto.class);
        return locationMapper.toDto(location);
    }

    public void deleteLocation(long id) {
        Location location = locations.stream()
                .filter(l -> l.getId() == id)
                .findFirst().orElseThrow(notFoundExeption(id));
        locations.remove(location);
    }

    public void deleteAllLocation() {
        counter.set(0);
        locations.clear();
    }

    private Supplier<LocationNotFoundException> notFoundExeption(long id) {
        return () -> new LocationNotFoundException("No location found with id: " + id);
    }
}
