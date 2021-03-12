package com.focuscorp.Dofan_Security.exception;

import org.apache.log4j.Logger;

public class UserNotFoundException extends Exception {

    private static final Logger logger = Logger.getLogger(UserNotFoundException.class);

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
