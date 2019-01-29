package com.play.flight.application.facade;

import com.play.flight.application.service.core.FlightSearchService;
import com.play.flight.application.service.validation.FlightSearchValidationService;
import com.play.flight.domain.dto.Searchable;
import com.play.flight.domain.dto.SearchableFlight;
import com.play.flight.domain.dto.SearchableFlightResponse;
import com.play.flight.domain.enumtype.FlightTicketCategoryType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlightSearchCommandTest {

    @InjectMocks
    private FlightSearchCommand sut;

    @Mock
    private FlightSearchService flightSearchService;

    @Mock
    private FlightSearchValidationService flightSearchValidationService;

    @Test
    void should_search_cheep_flights() {
        //given
        String category = "CHEAP";
        Searchable searchable = new Searchable();

        SearchableFlight mockCheapFlight = new SearchableFlight();
        mockCheapFlight.setCategory(FlightTicketCategoryType.CHEAP);
        SearchableFlightResponse mockResponse = SearchableFlightResponse.builder()
                .page(1)
                .count(10)
                .totalCount(20)
                .data(Arrays.asList(mockCheapFlight))
                .build();
        when(flightSearchService.search(category, searchable)).thenReturn(mockResponse);

        //when
        SearchableFlightResponse search = sut.search(category, searchable);

        //then
        assertThat(search).isNotNull();
        assertThat(search.getPage()).isEqualTo(1);
        assertThat(search.getCount()).isEqualTo(10);
        assertThat(search.getTotalCount()).isEqualTo(20);
        assertThat(search.getData()).hasAtLeastOneElementOfType(SearchableFlight.class);
        assertThat(search.getData().get(0).getCategory()).isEqualTo(FlightTicketCategoryType.CHEAP);

        InOrder inOrder = Mockito.inOrder(flightSearchValidationService, flightSearchService);
        inOrder.verify(flightSearchValidationService).validate("CHEAP");
        inOrder.verify(flightSearchService).search("CHEAP", searchable);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_search_business_flights() {
        //given
        String category = "BUSINESS";
        Searchable searchable = new Searchable();

        SearchableFlight mockCheapFlight = new SearchableFlight();
        mockCheapFlight.setCategory(FlightTicketCategoryType.BUSINESS);
        SearchableFlightResponse mockResponse = SearchableFlightResponse.builder()
                .page(1)
                .count(10)
                .totalCount(20)
                .data(Arrays.asList(mockCheapFlight))
                .build();
        when(flightSearchService.search(category, searchable)).thenReturn(mockResponse);

        //when
        SearchableFlightResponse search = sut.search(category, searchable);

        //then
        assertThat(search).isNotNull();
        assertThat(search.getPage()).isEqualTo(1);
        assertThat(search.getCount()).isEqualTo(10);
        assertThat(search.getTotalCount()).isEqualTo(20);
        assertThat(search.getData()).hasAtLeastOneElementOfType(SearchableFlight.class);
        assertThat(search.getData().get(0).getCategory()).isEqualTo(FlightTicketCategoryType.BUSINESS);

        InOrder inOrder = Mockito.inOrder(flightSearchValidationService, flightSearchService);
        inOrder.verify(flightSearchValidationService).validate("BUSINESS");
        inOrder.verify(flightSearchService).search("BUSINESS", searchable);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_search_all_flights() {
        //given
        String category = null;
        Searchable searchable = new Searchable();

        SearchableFlight mockCheapFlight = new SearchableFlight();
        mockCheapFlight.setCategory(FlightTicketCategoryType.BUSINESS);
        SearchableFlightResponse mockResponse = SearchableFlightResponse.builder()
                .page(1)
                .count(10)
                .totalCount(20)
                .data(Arrays.asList(mockCheapFlight))
                .build();
        when(flightSearchService.search(null, searchable)).thenReturn(mockResponse);

        //when
        SearchableFlightResponse search = sut.search(null, searchable);

        //then
        assertThat(search).isNotNull();
        assertThat(search.getPage()).isEqualTo(1);
        assertThat(search.getCount()).isEqualTo(10);
        assertThat(search.getTotalCount()).isEqualTo(20);
        assertThat(search.getData()).hasAtLeastOneElementOfType(SearchableFlight.class);
        assertThat(search.getData().get(0).getCategory()).isEqualTo(FlightTicketCategoryType.BUSINESS);

        InOrder inOrder = Mockito.inOrder(flightSearchValidationService, flightSearchService);
        inOrder.verify(flightSearchValidationService).validate(null);
        inOrder.verify(flightSearchService).search(null, searchable);
        inOrder.verifyNoMoreInteractions();
    }
}