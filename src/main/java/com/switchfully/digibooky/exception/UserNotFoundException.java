package com.switchfully.digibooky.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("User not found exception");
    }

}
