package com.switchfully.digibooky.exception;

public class IsbnNumberNotFoundException extends RuntimeException {
    public IsbnNumberNotFoundException() {
        super("ISBN was not found");
    }
}
