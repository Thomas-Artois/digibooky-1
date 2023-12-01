package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.Loan;
import com.switchfully.digibooky.dto.LoanDto;
import com.switchfully.digibooky.exception.BookNotFoundException;
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

    public LoansService(LoansRepository loansRepository, LoansMapper loansMapper) {
        this.loansRepository = loansRepository;
        this.loansMapper = loansMapper;
    }

    public LoanDto lendBook(String memberId, String isbnNumber) throws BookNotFoundException {
        checkIfIsbnNumberExists(isbnNumber);
        checkIfBookIsLentOutAlready(isbnNumber);

        return loansMapper.mapLoanToLoanDto(loansRepository.lendBook(memberId,isbnNumber));
    }

    public void checkIfIsbnNumberExists(String isbnNumber) throws BookNotFoundException {
        if (bookRepository.findSingleBookByIsbnNumber(isbnNumber) == null) {
            throw new BookNotFoundException();
        }
    }

    public void checkIfBookIsLentOutAlready(String isbnNumber) throws LoanAlreadyExistsException {
        if (!loansRepository.isIsbnNumberPresent(isbnNumber)){
            throw new LoanAlreadyExistsException();
        }
    }

}
