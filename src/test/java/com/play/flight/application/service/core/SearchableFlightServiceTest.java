package com.play.flight.application.service.core;

import com.play.flight.configuration.FlightStub;
import com.play.flight.domain.dto.Searchable;
import com.play.flight.domain.dto.SearchableFlight;
import com.play.flight.domain.dto.SearchableFlightResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SearchableFlightServiceTest {

    @InjectMocks
    private SearchableFlightService sut;

    @Test
    void should_search_default_case() {
        //given
        Searchable searchable = new Searchable();
        List<SearchableFlight> mockSearchableFlights = FlightStub.generateSearchableFlight();

        //when
        SearchableFlightResponse search = sut.search(mockSearchableFlights, searchable);

        //then
        assertThat(search).isNotNull();
        assertThat(search.getPage()).isEqualTo(1);
        assertThat(search.getCount()).isEqualTo(10);
        assertThat(search.getTotalCount()).isEqualTo(3);
        assertThat(search.getData()).hasSize(3);
    }

    @Test
    void should_return_empty_data_when_paging_wrong() {
        //given
        Searchable searchable = new Searchable();
        searchable.setPage(-1);
        List<SearchableFlight> mockSearchableFlights = FlightStub.generateSearchableFlight();

        //when
        SearchableFlightResponse search = sut.search(mockSearchableFlights, searchable);

        //then
        assertThat(search).isNotNull();
        assertThat(search.getPage()).isEqualTo(-1);
        assertThat(search.getCount()).isEqualTo(10);
        assertThat(search.getTotalCount()).isEqualTo(3);
        assertThat(search.getData()).hasSize(0);
    }
}