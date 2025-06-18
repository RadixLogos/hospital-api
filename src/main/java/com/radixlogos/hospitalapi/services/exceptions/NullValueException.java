package com.radixlogos.hospitalapi.services.exceptions;

public class NullValueException extends RuntimeException{

    public NullValueException(String msg){
        super(msg);
    }
}
