package com.play.flight.infrastructure.rest;

import com.play.flight.application.facade.FlightSearchCommand;
import com.play.flight.domain.dto.Searchable;
import com.play.flight.domain.dto.SearchableFlightResponse;
import com.play.flight.infrastructure.port.rest.FlightRestPort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/flights")
public class FlightController implements FlightRestPort {

    private final FlightSearchCommand flightSearchCommand;

    public FlightController(FlightSearchCommand flightSearchCommand) {
        this.flightSearchCommand = flightSearchCommand;
    }

    @Override
    @GetMapping
    public SearchableFlightResponse searchFlight(String category, Searchable searchable) {
        return flightSearchCommand.search(category, searchable);
    }
}
