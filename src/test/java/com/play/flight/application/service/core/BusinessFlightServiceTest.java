package com.play.flight.application.service.core;

import com.play.flight.application.service.client.BusinessFlightClientService;
import com.play.flight.domain.dto.BusinessFlight;
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
class BusinessFlightServiceTest {

    @InjectMocks
    private BusinessFlightService sut;

    @Mock
    private BusinessFlightClientService businessFlightClientService;

    @Test
    void should_send_request() {
        //given
        BusinessFlight businessFlight = new BusinessFlight();
        businessFlight.setFlight("Departure -> Arrival");

        when(businessFlightClientService.retrieve()).thenReturn(Arrays.asList(businessFlight));

        //when
        List<SearchableFlight> searchableFlights = sut.retrieveFlights();

        //then
        assertThat(searchableFlights).hasSize(1);
        assertThat(searchableFlights.get(0).getCategory()).isEqualTo(FlightTicketCategoryType.BUSINESS);
    }
}