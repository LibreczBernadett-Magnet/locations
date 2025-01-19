package com.namyxc.locations;

import com.namyxc.locations.dtos.LocationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class LocationsServiceTest {

    @Autowired
    private LocationsRepository repository;

    @Test
    void getLocations() {
        LocationsService locationsService = new LocationsService(new LocationProperties(), new LocationMapperImpl(), repository );
        List<LocationDto> locations  = locationsService.getLocations(java.util.Optional.empty());

        assertThat(locations.size()).isEqualTo(2);
    }
}