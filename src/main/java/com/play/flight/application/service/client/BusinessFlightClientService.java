package com.play.flight.application.service.client;

import com.play.flight.domain.dto.BusinessFlight;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BusinessFlightClientService {

    private static final String URL = "https://obscure-caverns-79008.herokuapp.com/business";

    public List<BusinessFlight> retrieve() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<BusinessFlight>> response = restTemplate.exchange(URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<BusinessFlight>>() {
                });
        return response.getBody();
    }
}
