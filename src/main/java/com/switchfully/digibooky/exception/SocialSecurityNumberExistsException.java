package com.switchfully.digibooky.exception;

public class SocialSecurityNumberExistsException extends RuntimeException{
    public SocialSecurityNumberExistsException() {
        super("Social security number exists exception");
    }

}
