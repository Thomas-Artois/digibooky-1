package com.switchfully.digibooky.exception;

public class IsbnNumberExistsException extends RuntimeException {

    public IsbnNumberExistsException() {
        super("ISBN already exists");
    }
}
