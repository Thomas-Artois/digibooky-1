package com.switchfully.digibooky.exception;

public class EmailExistsException extends RuntimeException{
        public EmailExistsException() {
            super("Email exists exception");
        }

}
