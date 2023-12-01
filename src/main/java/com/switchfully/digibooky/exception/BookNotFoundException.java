package com.switchfully.digibooky.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException() {
        super("This book doesn't exist exception");
    }
}
