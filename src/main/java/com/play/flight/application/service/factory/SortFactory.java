package com.play.flight.application.service.factory;

import com.play.flight.domain.dto.Searchable;
import com.play.flight.domain.dto.SearchableFlight;
import com.play.flight.domain.enumtype.SortField;
import com.play.flight.domain.enumtype.SortOrderType;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class SortFactory {

    public static Comparator<SearchableFlight> generateComparator(Searchable searchable) {
        SortField sort = searchable.getSort();
        SortOrderType order = searchable.getOrder();

        switch (sort) {
            case ARRIVAL:
                return compareByArrival(order);
            case ARRIVAL_TIME:
                return compareByArrivalTime(order);
            case DEPARTURE:
                return compareByDeparture(order);
            case DEPARTURE_TIME:
                return compareByDepartureTime(order);
            default:
                throw new IllegalArgumentException("Sort Field is unrecognized");
        }
    }

    private static Comparator<SearchableFlight> compareByArrival(SortOrderType order) {
        Comparator<SearchableFlight> comparing = Comparator.comparing(SearchableFlight::getArrival);
        if (order.equals(SortOrderType.DESC))
            comparing.reversed();
        return comparing;
    }

    private static Comparator<SearchableFlight> compareByArrivalTime(SortOrderType order) {
        Comparator<SearchableFlight> comparing = Comparator.comparing(SearchableFlight::getArrivalTime);
        if (order.equals(SortOrderType.DESC))
            comparing.reversed();
        return comparing;
    }

    private static Comparator<SearchableFlight> compareByDeparture(SortOrderType order) {
        Comparator<SearchableFlight> comparing = Comparator.comparing(SearchableFlight::getDeparture);
        if (order.equals(SortOrderType.DESC))
            comparing.reversed();
        return comparing;
    }

    private static Comparator<SearchableFlight> compareByDepartureTime(SortOrderType order) {
        Comparator<SearchableFlight> comparing = Comparator.comparing(SearchableFlight::getDepartureTime);
        if (order.equals(SortOrderType.DESC))
            comparing.reversed();
        return comparing;
    }

}
