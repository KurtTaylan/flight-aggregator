package com.play.flight.application.service.core;

import com.play.flight.domain.dto.Searchable;
import com.play.flight.domain.dto.SearchableFlight;
import com.play.flight.domain.dto.SearchableFlightResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FlightSearchService {

    private final CheapFlightService cheapFlightService;
    private final BusinessFlightService businessFlightService;
    private final SearchableFlightService searchableFlightService;

    public FlightSearchService(CheapFlightService cheapFlightService,
                               BusinessFlightService businessFlightService,
                               SearchableFlightService searchableFlightService) {
        this.cheapFlightService = cheapFlightService;
        this.businessFlightService = businessFlightService;
        this.searchableFlightService = searchableFlightService;
    }

    public SearchableFlightResponse search(String category, Searchable searchable) {
        List<SearchableFlight> searchableFlightList = new ArrayList<>();
        addCheapListIfAsked(category, searchableFlightList);
        addBusinessListIfAsked(category, searchableFlightList);
        return searchableFlightService.search(searchableFlightList, searchable);
    }

    private void addCheapListIfAsked(String category, List<SearchableFlight> searchableFlightList) {
        if (category == null || isExistingCategory(category, "CHEAP")) {
            List<SearchableFlight> cheapSearchableFlightList = cheapFlightService.retrieveFlights();
            if (!cheapSearchableFlightList.isEmpty()) {
                searchableFlightList.addAll(cheapSearchableFlightList);
            }
        }
    }

    private void addBusinessListIfAsked(String category, List<SearchableFlight> searchableFlightList) {
        if (category == null || isExistingCategory(category, "BUSINESS")) {
            List<SearchableFlight> businessSearchableFlightList = businessFlightService.retrieveFlights();
            if (!businessSearchableFlightList.isEmpty()) {
                searchableFlightList.addAll(businessSearchableFlightList);
            }
        }
    }

    private boolean isExistingCategory(String category, String selectedCategory) {
        return category != null && Objects.equals(category, selectedCategory);
    }
}
