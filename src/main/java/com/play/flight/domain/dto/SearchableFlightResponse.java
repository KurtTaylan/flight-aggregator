package com.play.flight.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchableFlightResponse implements Flight {

    private Integer page;
    private Integer count;
    private Integer totalCount;
    private List<SearchableFlight> data;
}
