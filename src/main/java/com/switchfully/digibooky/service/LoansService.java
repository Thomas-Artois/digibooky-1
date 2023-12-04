package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.Loan;
import com.switchfully.digibooky.dto.LoanDto;
import com.switchfully.digibooky.exception.BookNotFoundException;
import com.switchfully.digibooky.exception.DuplicateIsbnNumberException;
import com.switchfully.digibooky.exception.LoanAlreadyExistsException;
import com.switchfully.digibooky.exception.LoanDoesNotExistException;
import com.switchfully.digibooky.mapper.LoansMapper;
import com.switchfully.digibooky.repository.BookRepository;
import com.switchfully.digibooky.repository.LoansRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoansService {
    private final LoansRepository loansRepository;
    private final  LoansMapper loansMapper;
    private final BookRepository bookRepository;

    public LoansService(LoansRepository loansRepository, LoansMapper loansMapper, BookRepository bookRepository) {
        this.loansRepository = loansRepository;
        this.loansMapper = loansMapper;
        this.bookRepository = bookRepository;
    }

    public LoanDto lendBook(String memberId, String isbnNumber) throws BookNotFoundException {
        checkIfIsbnNumberExists(isbnNumber);
        checkIfBookIsLentOutAlready(isbnNumber);


        return loansMapper.mapLoanToLoanDto(loansRepository.lendBook(memberId,isbnNumber));
    }

    public String returnBook(String loanId) {
        String message = "Book was successfully returned";
        Loan loan = loansRepository.returnBook(loanId);

        if (loan.getDueDate().isBefore(LocalDate.now())) {
            message += " and it was overdue";
        }
        return message;
    }

    public void checkIfIsbnNumberExists(String isbnNumber) throws DuplicateIsbnNumberException {
        bookRepository.checkIfIsbnNumberExists(isbnNumber);
    }

    public void checkIfBookIsLentOutAlready(String isbnNumber) throws LoanAlreadyExistsException {
        if (loansRepository.isIsbnNumberPresent(isbnNumber)){
            throw new LoanAlreadyExistsException();
        }
    }

    public List<LoanDto> getAllLoans(String id) {
        return loansRepository.getAllLoansByMember(id).stream()
                .map(loansMapper::mapLoanToLoanDto)
                .collect(Collectors.toList());
    }

    public LoanDto getSingleLoanByIsbnNumber(String isbnNumber) {
        return loansMapper.mapLoanToLoanDto(loansRepository.findSingleLoanByIsbnNumber(isbnNumber));
    }
}
