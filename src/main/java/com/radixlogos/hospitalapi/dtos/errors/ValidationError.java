package com.radixlogos.hospitalapi.dtos.errors;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


public class ValidationError extends CustomError{
    List<FieldMessage> errors = new ArrayList<>();
    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addErrors(String field, String message) {
        var error = new FieldMessage(field,message);
        errors.add(error);
    }
}
