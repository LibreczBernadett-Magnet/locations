package com.namyxc.locations;

import com.namyxc.locations.dtos.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class LocationsRepositoryIT {

    @Autowired
    LocationsRepository repository;

    @Test
    void testPersist() {
        Location location = new Location("Test loc", 10,10);
        repository.save(location);
        List<Location> locations = repository.findAll();

        assertThat(locations)
                .extracting(Location::getName)
                .containsOnly("Test loc");
    }
}
