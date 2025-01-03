package com.namyxc.locations;

import com.namyxc.locations.dtos.Location;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LocationsServiceTest {

    @Test
    void getLocations() {
        LocationsService locationsService = new LocationsService();
        List<Location> locations  = locationsService.getLocations();

        assertThat(locations.size()).isEqualTo(5);
    }
}