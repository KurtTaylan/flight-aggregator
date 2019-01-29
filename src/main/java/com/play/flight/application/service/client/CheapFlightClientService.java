package com.play.flight.application.service.client;

import com.play.flight.domain.dto.CheapFlight;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CheapFlightClientService {

    private static final String URL = "https://obscure-caverns-79008.herokuapp.com/cheap";

    public List<CheapFlight> retrieve() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<CheapFlight>> response = restTemplate.exchange(URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CheapFlight>>() {
                });
        return response.getBody();
    }
}
