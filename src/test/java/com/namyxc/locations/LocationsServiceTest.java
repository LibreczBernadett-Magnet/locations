package com.namyxc.locations;

import com.namyxc.locations.dtos.LocationDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LocationsServiceTest {

    @Test
    void getLocations() {
        LocationsService locationsService = new LocationsService(new LocationMapperImpl());
        List<LocationDto> locations  = locationsService.getLocations(java.util.Optional.empty());

        assertThat(locations.size()).isEqualTo(5);
    }
}