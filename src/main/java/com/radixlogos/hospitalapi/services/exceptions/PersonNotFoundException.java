package com.radixlogos.hospitalapi.services.exceptions;

public class PersonNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PersonNotFoundException(String msg) {
        super(msg);
    }

    public PersonNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
