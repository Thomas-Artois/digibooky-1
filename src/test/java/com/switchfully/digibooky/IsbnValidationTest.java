package com.switchfully.digibooky;

import com.switchfully.digibooky.domain.IsbnValidation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IsbnValidationTest {
    @Test
    void givenValidIsbn_ThenReturnTrue(){
        //given
        String isbnNumber = "9780596528126";
        String isbnNumberWithHyphens = "978-0-306-40615-7";

        //when
        boolean actual = IsbnValidation.isIsbn13(isbnNumber);
        boolean actualWithHyphens = IsbnValidation.isIsbn13(isbnNumberWithHyphens);

        //then
        assertThat(actual).isTrue();
        assertThat(actualWithHyphens).isTrue();
    }

    @Test
    void givenInvalidIsbn_ThenReturnFalse(){
        //given
        String notAnIsbnNumber = "8780596528126";

        //when
        boolean actual = IsbnValidation.isIsbn13(notAnIsbnNumber);

        //then
        assertThat(actual).isFalse();
    }
}
