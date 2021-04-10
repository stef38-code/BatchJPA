package com.stef.spring.batch.jpa.exception;

public class OtherException extends CustomException{
    public OtherException() {
        this("Message par defaut");
    }

    public OtherException(String message) {
        super(message);
    }

    public OtherException(String message, Object... args) {
        super(message, args);
    }
}
