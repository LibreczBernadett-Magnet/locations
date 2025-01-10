package com.namyxc.locations;

import com.namyxc.locations.dtos.LocationDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {

    @Mock
    LocationsService locationsService;

    @InjectMocks
    LocationsController locationsController;

    @Test
    void getLocations() {
        List<LocationDto> mockedLocations = List.of(new LocationDto(0L, "mock", 0, 0));
        when(locationsService.getLocations(java.util.Optional.empty())).thenReturn(mockedLocations);
        List<LocationDto> locations = locationsController.getLocations(java.util.Optional.empty());

        assertThat(locations).isEqualTo(mockedLocations.toString());
    }
}