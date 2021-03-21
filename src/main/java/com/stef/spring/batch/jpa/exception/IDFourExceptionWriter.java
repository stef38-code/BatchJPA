package com.stef.spring.batch.jpa.exception;

public class IDFourExceptionWriter extends CustomException{
    public IDFourExceptionWriter() {
        this("Message par defaut");
    }

    public IDFourExceptionWriter(String message) {
        super(message);
    }

    public IDFourExceptionWriter(String message, Object... args) {
        super(message, args);
    }
}
