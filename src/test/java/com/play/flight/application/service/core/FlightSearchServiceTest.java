package com.play.flight.application.service.core;

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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlightSearchServiceTest {

    @InjectMocks
    private FlightSearchService sut;

    @Mock
    private CheapFlightService cheapFlightService;

    @Mock
    private BusinessFlightService businessFlightService;

    @Mock
    private SearchableFlightService searchableFlightService;

    @Test
    void should_search_cheap_flight() {
        //given
        String category = "CHEAP";
        Searchable searchable = new Searchable();

        List<SearchableFlight> mockCheapFlightList = new ArrayList<>();
        SearchableFlight mockCheapFlight = new SearchableFlight();
        mockCheapFlight.setCategory(FlightTicketCategoryType.CHEAP);
        mockCheapFlightList.add(mockCheapFlight);
        when(cheapFlightService.retrieveFlights()).thenReturn(mockCheapFlightList);

        SearchableFlightResponse mockSearchableFlightResponse = SearchableFlightResponse.builder().data(mockCheapFlightList).build();
        when(searchableFlightService.search(anyList(), any(Searchable.class))).thenReturn(mockSearchableFlightResponse);

        //when
        SearchableFlightResponse searchResponse = sut.search(category, searchable);

        //then
        assertThat(searchResponse).isNotNull();
        assertThat(searchResponse.getData()).hasSize(1);
        assertThat(searchResponse.getData().get(0).getCategory()).isEqualTo(FlightTicketCategoryType.CHEAP);

        InOrder inOrder = Mockito.inOrder(cheapFlightService, businessFlightService, searchableFlightService);
        inOrder.verify(cheapFlightService).retrieveFlights();
        inOrder.verify(businessFlightService, times(0)).retrieveFlights();
        inOrder.verify(searchableFlightService).search(anyList(), any(Searchable.class));
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_search_business_flight() {
        //given
        String category = "BUSINESS";
        Searchable searchable = new Searchable();

        List<SearchableFlight> mockBusinessFlightList = new ArrayList<>();
        SearchableFlight mockBusinessFlight = new SearchableFlight();
        mockBusinessFlight.setCategory(FlightTicketCategoryType.BUSINESS);
        mockBusinessFlightList.add(mockBusinessFlight);
        when(businessFlightService.retrieveFlights()).thenReturn(mockBusinessFlightList);

        SearchableFlightResponse mockSearchableFlightResponse = SearchableFlightResponse.builder().data(mockBusinessFlightList).build();
        when(searchableFlightService.search(anyList(), any(Searchable.class))).thenReturn(mockSearchableFlightResponse);

        //when
        SearchableFlightResponse searchResponse = sut.search(category, searchable);

        //then
        assertThat(searchResponse).isNotNull();
        assertThat(searchResponse.getData()).hasSize(1);
        assertThat(searchResponse.getData().get(0).getCategory()).isEqualTo(FlightTicketCategoryType.BUSINESS);

        InOrder inOrder = Mockito.inOrder(cheapFlightService, businessFlightService, searchableFlightService);
        inOrder.verify(cheapFlightService, times(0)).retrieveFlights();
        inOrder.verify(businessFlightService).retrieveFlights();
        inOrder.verify(searchableFlightService).search(anyList(), any(Searchable.class));
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_search_all_flight() {
        //given
        String category = null;
        Searchable searchable = new Searchable();

        List<SearchableFlight> mockCheapFlightList = new ArrayList<>();
        SearchableFlight mockCheapFlight = new SearchableFlight();
        mockCheapFlight.setCategory(FlightTicketCategoryType.CHEAP);
        mockCheapFlightList.add(mockCheapFlight);
        when(cheapFlightService.retrieveFlights()).thenReturn(mockCheapFlightList);

        List<SearchableFlight> mockBusinessFlightList = new ArrayList<>();
        SearchableFlight mockBusinessFlight = new SearchableFlight();
        mockBusinessFlight.setCategory(FlightTicketCategoryType.BUSINESS);
        mockBusinessFlightList.add(mockBusinessFlight);
        when(businessFlightService.retrieveFlights()).thenReturn(mockBusinessFlightList);

        List<SearchableFlight> mockAllFlightList = new ArrayList<>();
        mockAllFlightList.addAll(mockCheapFlightList);
        mockAllFlightList.addAll(mockBusinessFlightList);
        SearchableFlightResponse mockSearchableFlightResponse = SearchableFlightResponse.builder().data(mockAllFlightList).build();
        when(searchableFlightService.search(anyList(), any(Searchable.class))).thenReturn(mockSearchableFlightResponse);

        //when
        SearchableFlightResponse searchResponse = sut.search(category, searchable);

        //then
        assertThat(searchResponse).isNotNull();
        assertThat(searchResponse.getData()).hasSize(2);
        assertThat(searchResponse.getData().get(0).getCategory()).isEqualTo(FlightTicketCategoryType.CHEAP);
        assertThat(searchResponse.getData().get(1).getCategory()).isEqualTo(FlightTicketCategoryType.BUSINESS);

        InOrder inOrder = Mockito.inOrder(cheapFlightService, businessFlightService, searchableFlightService);
        inOrder.verify(cheapFlightService).retrieveFlights();
        inOrder.verify(businessFlightService).retrieveFlights();
        inOrder.verify(searchableFlightService).search(anyList(), any(Searchable.class));
        inOrder.verifyNoMoreInteractions();
    }
}