package com.namyxc.locations;

import com.namyxc.locations.dtos.LocationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocationsControllerWebClientIT {

    @MockBean
    LocationsService locationsService;

    @Autowired
    WebTestClient webClient;

    @Test
    void testCreateLocation() {
        when(locationsService.createLocation(any()))
                .thenReturn(new LocationDto(1L, "test1",1,1));

        webClient
                .post()
                .uri("/locations")
                .bodyValue(new LocationDto(1L, "test1",1,1))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(LocationDto.class).value(l -> assertEquals("test1", l.getName()));
    }

}
