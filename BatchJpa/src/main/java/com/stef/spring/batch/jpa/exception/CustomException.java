package com.stef.spring.batch.jpa.exception;

import java.text.MessageFormat;

/**
 * Exception qui permet de customiser les messages
 */
public class CustomException extends Exception{
    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

    /**
     * Personnaliser le message
     * @param message le message sous la forme "blabla {0} tititi {1}...etc"
     * @param args les arguments
     */
    public CustomException(String message,Object ...args) {
       super(Util.formattingMessage(message,args));
    }

    /**
     * Petite classe de mise en forme
     */
    private static class Util {
        public static String formattingMessage(String message, Object... args) {
            MessageFormat form = new MessageFormat(message);
            return form.format(args);
        }
    }
}
