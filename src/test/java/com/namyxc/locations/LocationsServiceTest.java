package com.namyxc.locations;

import com.namyxc.locations.dtos.LocationDto;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LocationsServiceTest {

    @Test
    void getLocations() {
        LocationsService locationsService = new LocationsService(new ModelMapper());
        List<LocationDto> locations  = locationsService.getLocations(java.util.Optional.empty());

        assertThat(locations.size()).isEqualTo(5);
    }
}