package com.play.flight.application.service.mapper;

import com.play.flight.domain.dto.BusinessFlight;
import com.play.flight.domain.dto.SearchableFlight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BusinessFlightMapper {

    static BusinessFlightMapper getIntance() {
        return Mappers.getMapper(BusinessFlightMapper.class);
    }

    @Mappings({
            @Mapping(target = "category", constant = "BUSINESS"),
            @Mapping(target = "id", expression = "java(String.valueOf(businessFlight.getUuid()))"),
            @Mapping(target = "departure", expression = "java(businessFlight.getFlight().split(\" -> \")[0])"),
            @Mapping(target = "arrival", expression = "java(businessFlight.getFlight().split(\" -> \")[1])"),
            @Mapping(target = "departureTime", source = "departure", dateFormat = "yyyy-MM-dd'T'HH:mm:ssZ"),
            @Mapping(target = "arrivalTime", source = "arrival", dateFormat = "yyyy-MM-dd'T'HH:mm:ssZ"),
    })
    SearchableFlight businessToSearchable(BusinessFlight businessFlight);


    List<SearchableFlight> businessListToSearchableList(List<BusinessFlight> businessFlightList);
}
