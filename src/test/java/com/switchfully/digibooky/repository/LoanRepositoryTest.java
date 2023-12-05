package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Loan;
import com.switchfully.digibooky.exception.LoanDoesNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class LoanRepositoryTest {

    private LoansRepository loansRepository;

    @BeforeEach
    void setUp() {
        loansRepository = new LoansRepository();
    }

    @Test
    void givenSingleLoanByIsbnNumber_whenIsbnExists_thenReturnLoan() {
        Loan loan = loansRepository.findSingleLoanByIsbnNumber("9785744653941");
        assertNotNull(loan);
        assertEquals("9785744653941",loan.getIsbnNumber());
    }

    @Test
    void givenSingleLoanByIsbnNumber_whenIsbnDoesntExists_thenReturnNull() {
        Loan loan = loansRepository.findSingleLoanByIsbnNumber("9785744653940");
        assertNull(loan);
    }

    @Test
    void givenSingleLoanByLoanId_whenLoanIdExists_thenReturnLoan() {
        Loan result = loansRepository.findSingleLoanByLoanId("8ec158fc-9b1b-46af-9e3f-5c99ee9752a9");
        assertNotNull(result);
        assertEquals("8ec158fc-9b1b-46af-9e3f-5c99ee9752a9", result.getLoanId());
    }

    @Test
    void givenSingleLoanByLoanId_whenLoanIdDoesntExist_thenReturnNull() {
        assertThrows(LoanDoesNotExistException.class, () ->
                loansRepository.findSingleLoanByLoanId("non-existing-id"));
    }

    @Test
    void givenBook_whenValidMemberIdAndIsbnNumber_ThenReturnLoan() {
        Loan loan = loansRepository.lendBook("8ec158fc-9b1b-46af-9e3f-5c99ee9752a9", "9785744653941");

        assertNotNull(loan);
        assertEquals("8ec158fc-9b1b-46af-9e3f-5c99ee9752a9", loan.getMemberId());
        assertEquals("9785744653941", loan.getIsbnNumber());
    }

}
