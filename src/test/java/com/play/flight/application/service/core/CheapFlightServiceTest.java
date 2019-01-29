package com.play.flight.application.service.core;

import com.play.flight.application.service.client.CheapFlightClientService;
import com.play.flight.domain.dto.CheapFlight;
import com.play.flight.domain.dto.SearchableFlight;
import com.play.flight.domain.enumtype.FlightTicketCategoryType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CheapFlightServiceTest {

    @InjectMocks
    private CheapFlightService sut;

    @Mock
    private CheapFlightClientService cheapFlightClientService;


    @Test
    void should_send_request() {
        //given
        when(cheapFlightClientService.retrieve()).thenReturn(Arrays.asList(new CheapFlight()));

        //when
        List<SearchableFlight> searchableFlights = sut.retrieveFlights();

        //then
        assertThat(searchableFlights).hasSize(1);
        assertThat(searchableFlights.get(0).getCategory()).isEqualTo(FlightTicketCategoryType.CHEAP);
    }
}