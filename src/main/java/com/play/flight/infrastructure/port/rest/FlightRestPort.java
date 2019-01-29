package com.play.flight.infrastructure.port.rest;

import com.play.flight.domain.dto.Searchable;
import com.play.flight.domain.dto.SearchableFlightResponse;

public interface FlightRestPort {

    SearchableFlightResponse searchFlight(String category, Searchable searchable);
}