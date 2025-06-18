package com.radixlogos.hospitalapi.services.exceptions;

public class HospitalAlreadyRegisteredException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public HospitalAlreadyRegisteredException(String msg) {
        super(msg);
    }

    public HospitalAlreadyRegisteredException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
