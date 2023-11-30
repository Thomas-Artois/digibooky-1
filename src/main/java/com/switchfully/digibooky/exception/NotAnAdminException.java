package com.switchfully.digibooky.exception;

public class NotAnAdminException extends RuntimeException{
    public NotAnAdminException() {
        super("Not an admin exception");
    }

}
