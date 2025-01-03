package com.namyxc.locations;

import com.namyxc.locations.dtos.Location;
import com.namyxc.locations.dtos.LocationDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

@Service
public class LocationsService {
    private ModelMapper modelMapper;

    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    private final List<Location> locations = Arrays.asList(
            new Location(0L,"Location - 0", 0,0),
            new Location(1L,"Location - 1", 1,-1),
            new Location(2L,"Location - 2", 2,-2),
            new Location(3L,"Location - 3", 3,-3),
            new Location(4L,"Location - 4", 4,-4)
    );

    public List<LocationDto> getLocations(){
        Type targetType = new TypeToken<List<LocationDto>>(){}.getType();
        return modelMapper.map(locations, targetType);
    }
}
