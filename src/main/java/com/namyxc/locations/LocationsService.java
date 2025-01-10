package com.namyxc.locations;

import com.namyxc.locations.dtos.Location;
import com.namyxc.locations.dtos.LocationDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class LocationsService {
    private final ModelMapper modelMapper;

    private final AtomicLong counter = new AtomicLong();

    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    private final List<Location> locations = Collections.synchronizedList(new ArrayList<>(List.of(
            new Location(counter.getAndIncrement(),"Location - 0", 0,0),
            new Location(counter.getAndIncrement(),"Location - 1", 1,-1),
            new Location(counter.getAndIncrement(),"Location - 2", 2,-2),
            new Location(counter.getAndIncrement(),"Location - 3", 3,-3),
            new Location(counter.getAndIncrement(),"Location - 4", 4,-4)
    )));

    public List<LocationDto> getLocations(Optional<String> name){
        Type targetType = new TypeToken<List<LocationDto>>(){}.getType();
        List<Location> filtered = locations.stream()
                .filter(location -> name.isEmpty() || location.getName().toLowerCase().contains(name.get().toLowerCase()))
                .toList();
        return modelMapper.map(filtered, targetType);
    }

    public LocationDto getLocation(long id) {
        return modelMapper.map(locations.stream()
                .filter(l -> l.getId() ==  id).findAny()
                .orElseThrow(() -> new IllegalArgumentException("No location found with id: " + id)),
                LocationDto.class);
    }

    public LocationDto createLocation(CreateLocationCommand command) {
        Location location = new Location(counter.getAndIncrement(), command.getName(), command.getLat(), command.getLon());
        locations.add(location);
        return modelMapper.map(location, LocationDto.class);
    }
}
