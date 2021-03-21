package com.stef.spring.batch.jpa.exception;

public class IDThreeExceptionProcessor extends CustomException{
    public IDThreeExceptionProcessor() {
        this("Message par defaut");
    }

    public IDThreeExceptionProcessor(String message) {
        super(message);
    }

    public IDThreeExceptionProcessor(String message, Object... args) {
        super(message, args);
    }
}
