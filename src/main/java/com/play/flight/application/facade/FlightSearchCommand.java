package com.play.flight.application.facade;

import com.play.flight.application.service.core.FlightSearchService;
import com.play.flight.application.service.validation.FlightSearchValidationService;
import com.play.flight.domain.dto.Searchable;
import com.play.flight.domain.dto.SearchableFlightResponse;
import org.springframework.stereotype.Service;

@Service
public class FlightSearchCommand {

    private final FlightSearchService flightSearchService;
    private final FlightSearchValidationService flightSearchValidationService;

    public FlightSearchCommand(FlightSearchService flightSearchService,
                               FlightSearchValidationService flightSearchValidationService) {
        this.flightSearchService = flightSearchService;
        this.flightSearchValidationService = flightSearchValidationService;
    }

    public SearchableFlightResponse search(String category, Searchable searchable) {
        flightSearchValidationService.validate(category);
        return flightSearchService.search(category, searchable);
    }
}
