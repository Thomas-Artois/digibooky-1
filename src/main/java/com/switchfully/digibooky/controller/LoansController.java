package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.domain.Message;
import com.switchfully.digibooky.domain.User;
import com.switchfully.digibooky.dto.LoanDto;
import com.switchfully.digibooky.service.LoansService;
import com.switchfully.digibooky.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoansController {

    private final LoansService loansService;
    private final UserService userService;

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

    //TODO: refactor endpoint path
    @PostMapping(path = "/returns", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Message returnBook(@RequestHeader String email, @RequestHeader String password, @RequestParam String loanId) {
        userService.checkIfUserIsMember(email, password);
        return new Message(loansService.returnBook(loanId));
    }
    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<LoanDto> getAllLoans(@RequestHeader String email, @RequestHeader String password, @RequestParam(required = false) String memberId,  @RequestParam(required = false) boolean isOverdue){
        userService.checkIfUserIsLibrarian(email, password);
        return  loansService.getAllLoans(memberId, isOverdue);
    }

}
