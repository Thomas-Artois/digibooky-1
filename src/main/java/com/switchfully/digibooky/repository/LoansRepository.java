package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Loan;
import com.switchfully.digibooky.exception.BookNotFoundException;
import com.switchfully.digibooky.exception.LoanAlreadyExistsException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LoansRepository {
    private Map<String, Loan> lentBooks = new HashMap<>();


    public Loan lendBook(String memberId, String isbnNumber) {
        Loan loan = new Loan(memberId, isbnNumber);
        lentBooks.put(isbnNumber, loan);
        return loan;
    }

    public boolean isIsbnNumberPresent(String isbnNumber) throws LoanAlreadyExistsException {
        return (lentBooks.get(isbnNumber) != null);
    }

}
