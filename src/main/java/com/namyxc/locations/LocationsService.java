package com.namyxc.locations;

import com.namyxc.locations.dtos.Location;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LocationsService {
    private final List<Location> locations = Arrays.asList(
            new Location(0L,"Location - 0", 0,0),
            new Location(1L,"Location - 1", 1,-1),
            new Location(2L,"Location - 2", 2,-2),
            new Location(3L,"Location - 3", 3,-3),
            new Location(4L,"Location - 4", 4,-4)
    );

    public List<Location> getLocations(){
        return locations;
    }
}
