package com.switchfully.digibooky.exception;

public class LoanAlreadyExistsException extends RuntimeException{
    public LoanAlreadyExistsException() {
        super("Loan exists exception");
    }

}