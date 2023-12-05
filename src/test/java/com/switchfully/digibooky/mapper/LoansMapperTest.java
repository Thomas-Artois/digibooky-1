package com.switchfully.digibooky.mapper;

import com.switchfully.digibooky.domain.Loan;
import com.switchfully.digibooky.dto.LoanDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class LoansMapperTest {
    private final LoansMapper loansMapper = new LoansMapper();

    @Test
    void givenLoan_whenMapLoanToLoanDto_thenGetLoanDto() {
        // GIVEN
        String memberId = "11e6fc25-ba21-450a-9aeb-4e7bc98e03ff";
        String isbnNumber = "9781234567897";

        Loan loan = new Loan(memberId, isbnNumber);

        // WHEN
        LoanDto actual = loansMapper.mapLoanToLoanDto(loan);

        // THEN
        assertThat(actual).isInstanceOf(LoanDto.class);
        assertThat(actual.getMemberId()).isEqualTo(memberId);
        assertThat(actual.getIsbnNumber()).isEqualTo(isbnNumber);

    }
}