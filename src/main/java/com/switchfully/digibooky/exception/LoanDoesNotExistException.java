package com.switchfully.digibooky.exception;

public class LoanDoesNotExistException extends RuntimeException{
    public LoanDoesNotExistException() {
        super("Loan does not exist exception");
    }
}
