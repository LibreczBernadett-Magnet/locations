package com.namyxc.locations;

import com.namyxc.locations.dtos.LocationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
class LocationsServiceTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() throws Exception {
        DbInitializer dbInitializer = new DbInitializer(jdbcTemplate);
        dbInitializer.run();
    }

    @Test
    void getLocations() {
        LocationsService locationsService = new LocationsService(new LocationProperties(), new LocationMapperImpl(), new LocationsDao(jdbcTemplate));
        List<LocationDto> locations  = locationsService.getLocations(java.util.Optional.empty());

        assertThat(locations.size()).isEqualTo(2);
    }
}