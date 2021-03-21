package com.stef.spring.batch.jpa.exception;

public class IDTwoExceptionReader extends CustomException{
    public IDTwoExceptionReader() {
        this("Message par defaut");
    }
    public IDTwoExceptionReader(String message) {
        super(message);
    }

    public IDTwoExceptionReader(String message, Object... args) {
        super(message, args);
    }
}
