package com.play.flight.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.play.flight.domain.dto.BusinessFlight;
import com.play.flight.domain.dto.CheapFlight;
import com.play.flight.domain.dto.SearchableFlight;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FlightStub {

    public static List<SearchableFlight> generateSearchableFlight() {
        String filePath = "/data/searchable.json";
        ObjectMapper mapper = new ObjectMapper().registerModule(new JodaModule());
        TypeReference<List<SearchableFlight>> mapType = new TypeReference<List<SearchableFlight>>() {
        };
        InputStream is = TypeReference.class.getResourceAsStream(filePath);
        List<SearchableFlight> flightList = new ArrayList<>();
        try {
            flightList = mapper.readValue(is, mapType);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return flightList;
    }

    public static List<CheapFlight> generateCheaperFlight() {
        String filePath = "/data/cheap.json";
        ObjectMapper mapper = new ObjectMapper().registerModule(new JodaModule());
        TypeReference<List<CheapFlight>> mapType = new TypeReference<List<CheapFlight>>() {
        };
        InputStream is = TypeReference.class.getResourceAsStream(filePath);
        List<CheapFlight> flightList = new ArrayList<>();
        try {
            flightList = mapper.readValue(is, mapType);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return flightList;
    }

    public static List<BusinessFlight> generateBusinessFlight() {
        String filePath = "/data/business.json";

        ObjectMapper mapper = new ObjectMapper().registerModule(new JodaModule());
        TypeReference<List<BusinessFlight>> mapType = new TypeReference<List<BusinessFlight>>() {
        };
        InputStream is = TypeReference.class.getResourceAsStream(filePath);
        List<BusinessFlight> flightList = new ArrayList<>();
        try {
            flightList = mapper.readValue(is, mapType);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return flightList;
    }
}
