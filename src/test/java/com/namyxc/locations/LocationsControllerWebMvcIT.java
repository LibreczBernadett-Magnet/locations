package com.namyxc.locations;

import com.namyxc.locations.dtos.LocationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LocationsController.class)
public class LocationsControllerWebMvcIT {

    @MockBean
    LocationsService locationsService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testListLocations() throws Exception {
        when(locationsService.getLocations(any()))
                .thenReturn(List.of(
                        new LocationDto(1L, "test1", 1,1),
                        new LocationDto(2L, "test2", 1,1)
                ));

        mockMvc.perform(get("/locations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", equalTo("test1")));
    }
}
