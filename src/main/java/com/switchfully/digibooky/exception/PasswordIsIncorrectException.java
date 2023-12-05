package com.switchfully.digibooky.exception;

public class PasswordIsIncorrectException extends RuntimeException{
    public PasswordIsIncorrectException() {
        super("Password is incorrect exception");
    }

}
