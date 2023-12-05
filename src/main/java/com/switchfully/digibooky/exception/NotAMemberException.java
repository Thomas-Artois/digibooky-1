package com.switchfully.digibooky.exception;

public class NotAMemberException extends RuntimeException{
    public NotAMemberException() {
        super("Not a member exception");
    }

}
