package com.play.flight.domain.dto;

import com.play.flight.domain.enumtype.SortField;
import com.play.flight.domain.enumtype.SortOrderType;
import lombok.Data;

@Data
public class Searchable {

    private Integer page = 1;
    private Integer count = 10;
    private SortOrderType order = SortOrderType.ASC;
    private SortField sort = SortField.DEPARTURE_TIME;
}
