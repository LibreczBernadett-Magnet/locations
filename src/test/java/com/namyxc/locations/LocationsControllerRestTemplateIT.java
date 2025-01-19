package com.namyxc.locations;

import com.namyxc.locations.dtos.LocationDto;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from locations")
public class LocationsControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @RepeatedTest(2)
    void testListLocations() {

        template.postForObject("/locations", new CreateLocationCommand("test1", 1,1), LocationDto.class);
        template.postForObject("/locations", new CreateLocationCommand("test2", 2,2), LocationDto.class);

        List<LocationDto> locations = template.exchange("/locations",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LocationDto>>() {})
                .getBody();

        assertThat(locations)
                .extracting(LocationDto::getName)
                .containsExactly("test1", "test2");
    }
}
