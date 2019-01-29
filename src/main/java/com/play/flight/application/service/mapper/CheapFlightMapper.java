package com.play.flight.application.service.mapper;

import com.play.flight.domain.dto.CheapFlight;
import com.play.flight.domain.dto.SearchableFlight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CheapFlightMapper {

    static CheapFlightMapper getIntance() {
        return Mappers.getMapper(CheapFlightMapper.class);
    }


    @Mappings({
            @Mapping(target = "category", constant = "CHEAP"),
    })
    SearchableFlight cheaperToSearchable(CheapFlight cheapFlight);


    List<SearchableFlight> cheaperListToSearchableList(List<CheapFlight> cheapFlightList);
}
