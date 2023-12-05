package com.switchfully.digibooky.exception;

public class DuplicateIsbnNumberException extends RuntimeException {

    public DuplicateIsbnNumberException() {
        super("ISBN already exists");
    }
}
