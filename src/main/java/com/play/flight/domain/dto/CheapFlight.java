package com.play.flight.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.play.flight.infrastructure.configuration.time.epoch.JsonJodaEpochDateTimeDeserializer;
import com.play.flight.infrastructure.configuration.time.epoch.JsonJodaEpochDateTimeSerializer;
import lombok.Data;
import org.joda.time.DateTime;

@Data
public class CheapFlight implements Flight {

    private String id;
    private String departure;
    private String arrival;

    @JsonProperty("departureTime")
    @JsonSerialize(using = JsonJodaEpochDateTimeSerializer.class)
    @JsonDeserialize(using = JsonJodaEpochDateTimeDeserializer.class)
    private DateTime departureTime;

    @JsonProperty("arrivalTime")
    @JsonSerialize(using = JsonJodaEpochDateTimeSerializer.class)
    @JsonDeserialize(using = JsonJodaEpochDateTimeDeserializer.class)
    private DateTime arrivalTime;
}
