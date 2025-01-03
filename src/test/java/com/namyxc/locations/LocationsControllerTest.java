package com.namyxc.locations;

import com.namyxc.locations.dtos.Location;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {

    @Mock
    LocationsService locationsService;

    @InjectMocks
    LocationsController locationsController;

    @Test
    void getLocations() {
        List<Location> mockedLocations = List.of(new Location(0L, "mock", 0, 0));
        when(locationsService.getLocations()).thenReturn(mockedLocations);
        String locations = locationsController.getLocations();

        assertThat(locations).isEqualTo(mockedLocations.toString());
    }
}