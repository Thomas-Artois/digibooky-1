package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.Loan;
import com.switchfully.digibooky.dto.LoanDto;
import com.switchfully.digibooky.exception.BookNotFoundException;
import com.switchfully.digibooky.exception.LoanAlreadyExistsException;
import com.switchfully.digibooky.exception.NotALibrarianException;
import com.switchfully.digibooky.service.LoansService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/loans")
public class LoansController {

    private LoansService loansService;

    public LoansController(LoansService loansService) {
        this.loansService = loansService;
    }

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public LoanDto lendBook(@RequestParam String memberId, @RequestParam String isbnNumber) {
        return loansService.lendBook(memberId,isbnNumber);
    }

    @ExceptionHandler(BookNotFoundException.class)
    private void bookNotFoundException(BookNotFoundException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(LoanAlreadyExistsException.class)
    private void loanAlreadyExistsException(LoanAlreadyExistsException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }

}
