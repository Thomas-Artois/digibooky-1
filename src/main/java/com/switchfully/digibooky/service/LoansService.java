package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.Loan;
import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.dto.LoanDto;
import com.switchfully.digibooky.exception.BookNotFoundException;
import com.switchfully.digibooky.exception.DuplicateIsbnNumberException;
import com.switchfully.digibooky.exception.LoanAlreadyExistsException;
import com.switchfully.digibooky.mapper.LoansMapper;
import com.switchfully.digibooky.repository.BookRepository;
import com.switchfully.digibooky.repository.LoansRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        // TODO: add exception handling
        return loansRepository.returnBook(loanId);
    }

    public void checkIfIsbnNumberExists(String isbnNumber) throws DuplicateIsbnNumberException {
        bookRepository.checkIfIsbnNumberExists(isbnNumber);
    }

    public void checkIfBookIsLentOutAlready(String isbnNumber) throws LoanAlreadyExistsException {
        if (loansRepository.isIsbnNumberPresent(isbnNumber)){
            throw new LoanAlreadyExistsException();
        }
    }

    public List<LoanDto> getAllLoans(String memberId, boolean isOverDue) {
        Stream<Loan> loanList = loansRepository.findAllLoans().stream();

        if (memberId != null) {
            loanList = findLoansByMemberId(memberId, loanList);
        }
        if (isOverDue) {
            loanList = findLoansByOverdue(loanList);
        }
        return loanList
                .map(loansMapper::mapLoanToLoanDto)
                .collect(Collectors.toList());
    }

    private Stream<Loan> findLoansByOverdue(Stream<Loan> stream) {
        return stream.filter(
                loan -> loan.getDueDate().isBefore(LocalDate.now()));
    }

    private Stream<Loan> findLoansByMemberId(String memberId, Stream<Loan> stream) {
        return stream.filter(
                loan -> loan.getMemberId().equals(memberId));
    }
}
