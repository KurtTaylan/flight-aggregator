package com.play.flight.application.service.core;

import com.play.flight.application.service.client.CheapFlightClientService;
import com.play.flight.application.service.mapper.CheapFlightMapper;
import com.play.flight.domain.dto.SearchableFlight;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheapFlightService {

    private final CheapFlightClientService cheapFlightClientService;

    public CheapFlightService(CheapFlightClientService cheapFlightClientService) {
        this.cheapFlightClientService = cheapFlightClientService;
    }

    public List<SearchableFlight> retrieveFlights() {
        return cheapFlightClientService.retrieve().stream()
                .map(cheapFlight -> CheapFlightMapper.getIntance().cheaperToSearchable(cheapFlight))
                .collect(Collectors.toList());

    }
}
