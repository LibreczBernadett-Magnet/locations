package com.namyxc.locations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventstoreGatewayWireMockIT {

    static final String host = "localhost";

    static final int port = 8081;

    static WireMockServer wireMockServer;

    @BeforeAll
    static void startServer() {
        wireMockServer = new WireMockServer(wireMockConfig().port(port));
        WireMock.configureFor(host, port);
        wireMockServer.start();
    }

    @AfterAll
    static void stopServer() {
        wireMockServer.stop();
    }

    @BeforeEach
    void resetServer() {
        wireMockServer.resetAll();
    }

    @Test
    void testEventstore() throws JsonProcessingException {
        String resource = "/api/events";

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(new EventDto(1, "test", "2025.01.01", "Created new location: X"));

        stubFor(get(urlPathEqualTo(resource))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(json)
                )
        );

        EventstoreGateway eventstoreGateway = new EventstoreGateway(new RestTemplateBuilder());
        CreateEventCommand createEventCommand = new CreateEventCommand("Created new location: X");
        EventDto eventDto = eventstoreGateway.sendEvent(createEventCommand);

        verify(postRequestedFor(urlPathEqualTo(resource)));

        assertEquals("test", eventDto.getSource());
    }
}
