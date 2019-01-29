package com.play.flight.application.service.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(MockitoExtension.class)
class FlightSearchValidationServiceTest {

    @InjectMocks
    private FlightSearchValidationService sut;

    @Test
    void should_validate_when_category_is_not_present() {
        //given
        String category = null;

        //when
        Throwable throwable = catchThrowable(() -> sut.validate(category));

        //then
        assertThat(throwable).isNull();
    }

    @Test
    void should_validatewhen_category_is_not_present_and_valid() {
        //given
        String category = "CHEAP";

        //when
        Throwable throwable = catchThrowable(() -> sut.validate(category));

        //then
        assertThat(throwable).isNull();
    }

    @Test
    void should_throw_exception_when_category_is_not_expected() {
        //given
        String category = "NOT-EXPECTED-CATEGORY";

        //when
        Throwable throwable = catchThrowable(() -> sut.validate(category));

        //then
        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Category is not valid");
    }
}