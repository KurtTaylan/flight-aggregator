package com.play.flight.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.play.flight.domain.enumtype.FlightTicketCategoryType;
import com.play.flight.infrastructure.configuration.time.formatter.JsonJodaDateTimeDeserializer;
import com.play.flight.infrastructure.configuration.time.formatter.JsonJodaDateTimeSerializer;
import lombok.Data;
import org.joda.time.DateTime;

@Data
public class SearchableFlight implements Flight {

    private FlightTicketCategoryType category;
    private String id;
    private String departure;
    private String arrival;

    @JsonProperty("departureTime")
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    @JsonDeserialize(using = JsonJodaDateTimeDeserializer.class)
    private DateTime departureTime;

    @JsonProperty("arrivalTime")
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    @JsonDeserialize(using = JsonJodaDateTimeDeserializer.class)
    private DateTime arrivalTime;
}
