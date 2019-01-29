package com.play.flight.application.service.validation;

import com.play.flight.domain.enumtype.FlightTicketCategoryType;
import org.springframework.stereotype.Service;

@Service
public class FlightSearchValidationService {

    public void validate(String category) {
        if (category != null) {
            try {
                FlightTicketCategoryType.valueOf(category);
            } catch (Exception e) {
                throw new IllegalArgumentException("Category is not valid");
            }
        }
    }
}
