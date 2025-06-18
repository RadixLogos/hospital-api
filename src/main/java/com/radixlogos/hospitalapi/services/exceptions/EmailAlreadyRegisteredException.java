package com.radixlogos.hospitalapi.services.exceptions;

public class EmailAlreadyRegisteredException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailAlreadyRegisteredException(String msg) {
        super(msg);
    }

    public EmailAlreadyRegisteredException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
