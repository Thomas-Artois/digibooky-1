package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.Loan;
import com.switchfully.digibooky.exception.BookNotFoundException;
import com.switchfully.digibooky.exception.LoanAlreadyExistsException;
import com.switchfully.digibooky.exception.LoanDoesNotExistException;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class LoansRepository {
    private final Map<String, Loan> lentBooks = new HashMap<>();


    public LoansRepository() {
        List<Loan> listOfLoans = List.of(
                new Loan("8ec158fc-9b1b-46af-9e3f-5c99ee9752a9","9785744653941"),
                new Loan("8ec158fc-9b1b-46af-9e3f-5c99ee9752a9", "9784578421634"),
                new Loan("7dcd50bb-98bb-49a0-96ac-e59c3fc7f9a0", "9781063599397")

        );
        listOfLoans.forEach(this::create);
    }
    public Loan lendBook(String memberId, String isbnNumber) {
        Loan loan = new Loan(memberId, isbnNumber);
        lentBooks.put(isbnNumber, loan);
        return loan;
    }

    public Loan create(Loan loan) {
        lentBooks.put(loan.getIsbnNumber(), loan);
        return loan;
    }

    public String returnBook() {
        return null;
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

    public List<Loan> getAllLoansByMember(String id) {
        return lentBooks.values().stream()
                .filter(loan -> loan.getMemberId().equals(id))
                .collect(Collectors.toList());
    }
}
