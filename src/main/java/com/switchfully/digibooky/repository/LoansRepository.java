package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Loan;
import com.switchfully.digibooky.exception.BookNotFoundException;
import com.switchfully.digibooky.exception.LoanAlreadyExistsException;
import com.switchfully.digibooky.exception.LoanDoesNotExistException;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Repository
public class LoansRepository {
    private final Map<String, Loan> lentBooks = new HashMap<>();

    public Loan findSingleLoanByIsbnNumber(String isbnNumber) {
        return lentBooks.get(isbnNumber);
    }

    public Loan lendBook(String memberId, String isbnNumber) {
        Loan loan = new Loan(memberId, isbnNumber);
        lentBooks.put(isbnNumber, loan);
        return loan;
    }

    public String returnBook(String loanId) throws LoanDoesNotExistException, BookNotFoundException {
        String message;

        boolean isOverdue = lentBooks.values().stream()
                .filter(loan -> loan.getLoanId().equals(loanId))
                .map(Loan::getDueDate)
                .findFirst().orElseThrow(LoanDoesNotExistException::new).isBefore(LocalDate.now());

        boolean isReturned = lentBooks.entrySet().removeIf(map -> map.getValue().getLoanId().equals(loanId));

        if (isReturned) {
            message = "Book was successfully returned";
            if (isOverdue) {
                message += " and it was overdue";
            }
        } else {
            throw new BookNotFoundException();
        }

        return message;
    }

    public boolean isIsbnNumberPresent(String isbnNumber) throws LoanAlreadyExistsException {
        return (lentBooks.get(isbnNumber) != null);
    }

}
