package com.namyxc.locations;

import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventstoreGateway {
/*
    private final RestTemplate restTemplate;

    public EventDto sendEvent(CreateEventCommand command) {
        return restTemplate.postForObject("http://localhost:8081/api/events", command, EventDto.class);
    }
    */

    private final JmsTemplate jmsTemplate;

    public void sendMessage(Long id) {
        jmsTemplate.convertAndSend("eventsQueue", new CreateEventCommand("Location has been created/updated: " + id));
    }
}
