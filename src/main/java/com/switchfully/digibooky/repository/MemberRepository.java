package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Loan;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberRepository {
    private Map<String, Loan> lentBooks = new HashMap<>();

    public Loan lendBook(String memberId, String isbnNumber) {
        Loan loan = new Loan(memberId,isbnNumber);
        lentBooks.put(isbnNumber, loan);
        return loan;
    }

}
