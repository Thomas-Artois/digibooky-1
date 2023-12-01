package com.switchfully.digibooky.service;

import com.switchfully.digibooky.dto.LoanDto;
import com.switchfully.digibooky.exception.BookNotFoundException;
import com.switchfully.digibooky.exception.DuplicateIsbnNumberException;
import com.switchfully.digibooky.exception.LoanAlreadyExistsException;
import com.switchfully.digibooky.mapper.LoansMapper;
import com.switchfully.digibooky.repository.BookRepository;
import com.switchfully.digibooky.repository.LoansRepository;
import org.springframework.stereotype.Service;

@Service
public class LoansService {
    private LoansRepository loansRepository;
    private LoansMapper loansMapper;
    private BookRepository bookRepository;

    public LoansService(LoansRepository loansRepository, LoansMapper loansMapper, BookRepository bookRepository) {
        this.loansRepository = loansRepository;
        this.loansMapper = loansMapper;
        this.bookRepository = bookRepository;
    }

    public LoanDto lendBook(String memberId, String isbnNumber) throws BookNotFoundException {
        System.out.println("loansService: " + isbnNumber);
        checkIfIsbnNumberExists(isbnNumber);
        checkIfBookIsLentOutAlready(isbnNumber);


        return loansMapper.mapLoanToLoanDto(loansRepository.lendBook(memberId,isbnNumber));
    }

    public void checkIfIsbnNumberExists(String isbnNumber) throws DuplicateIsbnNumberException {
        System.out.println("loansService - check if isbn exists: " + isbnNumber);
        bookRepository.checkIfIsbnNumberExists(isbnNumber);
    }

    public void checkIfBookIsLentOutAlready(String isbnNumber) throws LoanAlreadyExistsException {
        System.out.println("loansService - check if book is lent: " + isbnNumber);
        if (loansRepository.isIsbnNumberPresent(isbnNumber)){
            throw new LoanAlreadyExistsException();
        }
    }

}
