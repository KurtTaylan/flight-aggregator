package com.play.flight.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.play.flight.infrastructure.configuration.time.formatter.JsonJodaDateTimeDeserializer;
import com.play.flight.infrastructure.configuration.time.formatter.JsonJodaDateTimeSerializer;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.UUID;

@Data
public class BusinessFlight implements Flight {

    private UUID uuid;
    private String flight;

    @JsonProperty("departure")
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    @JsonDeserialize(using = JsonJodaDateTimeDeserializer.class)
    private DateTime departure;

    @JsonProperty("arrival")
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    @JsonDeserialize(using = JsonJodaDateTimeDeserializer.class)
    private DateTime arrival;

}
