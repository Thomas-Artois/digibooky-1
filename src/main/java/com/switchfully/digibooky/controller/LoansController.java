package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.domain.User;
import com.switchfully.digibooky.dto.LoanDto;
import com.switchfully.digibooky.exception.BookNotFoundException;
import com.switchfully.digibooky.exception.LoanAlreadyExistsException;
import com.switchfully.digibooky.exception.NotAMemberException;
import com.switchfully.digibooky.service.LoansService;
import com.switchfully.digibooky.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/loans")
public class LoansController {

    private LoansService loansService;
    private UserService userService;

    public LoansController(LoansService loansService, UserService userService) {
        this.loansService = loansService;
        this.userService = userService;
    }

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public LoanDto lendBook(@RequestHeader String email, @RequestHeader String password, @RequestParam String isbnNumber) {
        userService.checkIfUserIsMember(email, password);
        User user = userService.getUserByEmail(email);
        return loansService.lendBook(user.getId(),isbnNumber);
    }

    @ExceptionHandler(BookNotFoundException.class)
    private void bookNotFoundException(BookNotFoundException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(LoanAlreadyExistsException.class)
    private void loanAlreadyExistsException(LoanAlreadyExistsException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(NotAMemberException.class)
    private void notAMemberException(NotAMemberException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
    }

    //TODO add user handler

}
