package com.namyxc.locations;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EventstoreGateway {

    private final RestTemplate restTemplate;

    public EventstoreGateway(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public EventDto sendEvent(CreateEventCommand command) {
        return restTemplate.postForObject("http://localhost:8081/api/events", command, EventDto.class);
    }
}
