package com.namyxc.locations;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.with;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@AutoConfigureMockMvc
public class LocationsControllerRestAssuredIT {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        RestAssuredMockMvc.requestSpecification =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON);
    }

    @Test
    void testDeleteLocation() {
        Integer id = with()
                .body(new CreateLocationCommand("Delete", 1, 1))
                .post("/locations")
                .then()
                .statusCode(201)
                .body("name", equalTo("Delete"))
                .extract().path("id");

        with()
                .delete("/locations/" + id)
                .then()
                .statusCode(204);
    }

    @Test
    void validate() {
        with()
                .body(new CreateLocationCommand("Valid", 1, 1))
                .post("/locations")
                .then()
                .body(matchesJsonSchemaInClasspath("location-dto.json"));
    }

    @Test
    void validateCreateLocation() {
        with()
        .body(new CreateLocationCommand("", 1, 1))
                .post("/locations")
                .then()
                .statusCode(400);
    }

    @Test
    void validateUpdateLocation() {
        Integer id = with()
                .body(new CreateLocationCommand("Update", 1, 1))
                .post("/locations")
                .then()
                .statusCode(201)
                .body("name", equalTo("Update"))
                .extract().path("id");

        with()
                .body(new CreateLocationCommand("Update2", 1000, 1))
                .put("/locations/" + id)
                .then()
                .statusCode(400);
    }
}
