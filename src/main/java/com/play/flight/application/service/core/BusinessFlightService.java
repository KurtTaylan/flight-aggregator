package com.play.flight.application.service.core;

import com.play.flight.application.service.client.BusinessFlightClientService;
import com.play.flight.application.service.mapper.BusinessFlightMapper;
import com.play.flight.domain.dto.SearchableFlight;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessFlightService {

    private final BusinessFlightClientService businessFlightClientService;

    public BusinessFlightService(BusinessFlightClientService businessFlightClientService) {
        this.businessFlightClientService = businessFlightClientService;
    }

    public List<SearchableFlight> retrieveFlights() {
        return businessFlightClientService.retrieve()
                .stream()
                .map(businessFlight ->
                        BusinessFlightMapper.getIntance().businessToSearchable(businessFlight))
                .collect(Collectors.toList());
    }
}
