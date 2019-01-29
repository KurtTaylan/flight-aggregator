package com.play.flight.infrastructure.api.rest;

import com.play.flight.domain.enumtype.SortOrderType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SearchableFlightControllerIT {

    @LocalServerPort
    private int randomServerPort;

    @Test
    void should_search_flights() {
        Integer page = 1;
        Integer count = 10;
        SortOrderType order = SortOrderType.ASC;

        given()
                .port(randomServerPort)
                .contentType("application/json")
                .queryParam("page", page)
                .queryParam("count", count)
                .queryParam("order", order)
        .when()
                .get("/flights")

        .then()
                .statusCode(200)
        .body("", Matchers.notNullValue())
        .body("page", Matchers.equalTo(1))
        .body("count", Matchers.equalTo(10))
        .body("totalCount", Matchers.notNullValue())
        .body("data", is(not(empty())));
    }
}