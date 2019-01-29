package com.play.flight.application.service.core;

import com.play.flight.application.service.factory.SortFactory;
import com.play.flight.domain.dto.Searchable;
import com.play.flight.domain.dto.SearchableFlight;
import com.play.flight.domain.dto.SearchableFlightResponse;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class SearchableFlightService {

    public SearchableFlightResponse search(List<SearchableFlight> searchableFlightList, Searchable searchable) {
        sortList(searchableFlightList, searchable);
        List<SearchableFlight> requestedFlights = splitPortion(searchableFlightList, searchable);
        return mapToResponse(searchable, requestedFlights, searchableFlightList);
    }

    private void sortList(List<SearchableFlight> searchableFlightList, Searchable searchable) {
        Comparator<SearchableFlight> comparator = SortFactory.generateComparator(searchable);
        searchableFlightList.sort(comparator);
    }

    private List<SearchableFlight> splitPortion(List<SearchableFlight> searchableFlightList, Searchable searchable) {
        Integer scrollBeginIndex = (searchable.getPage() - 1) * searchable.getCount();
        Integer scrollEndIndex = scrollBeginIndex + searchable.getCount();

        int listSize = searchableFlightList.size();
        if (scrollEndIndex > listSize) {
            scrollEndIndex = listSize;
        }

        if (scrollBeginIndex < 0 || scrollBeginIndex > scrollEndIndex)
            return Lists.emptyList();
        else
            return searchableFlightList.subList(scrollBeginIndex, scrollEndIndex);
    }

    private SearchableFlightResponse mapToResponse(Searchable searchable, List<SearchableFlight> requestedFlights,
                                                   List<SearchableFlight> searchableFlightList) {
        return SearchableFlightResponse.builder()
                .totalCount(searchableFlightList.size())
                .count(searchable.getCount())
                .page(searchable.getPage())
                .data(requestedFlights)
                .build();
    }
}
