package com.switchfully.digibooky;

import com.switchfully.digibooky.domain.IsbnValidation;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class IsbnValidationTest {
    @Test
    void givenValidIsbn_ThenReturnTrue(){
        //given
        String isbnNumber = "9780596528126";

        //when
        boolean actual = IsbnValidation.isIsbn13(isbnNumber);

        //then
        assertThat(actual).isTrue();
    }

    @Test
    void givenInvalidIsbn_ThenReturnFalse(){
        //given
        String isbnNumber = "8780596528126";

        //when
        boolean actual = IsbnValidation.isIsbn13(isbnNumber);

        //then
        assertThat(actual).isFalse();
    }
}
