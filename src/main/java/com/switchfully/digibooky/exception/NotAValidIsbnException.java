package com.switchfully.digibooky.exception;

public class NotAValidIsbnException extends RuntimeException {
    public NotAValidIsbnException () {
        super("Not a valid Isbn Number");
    }
}
