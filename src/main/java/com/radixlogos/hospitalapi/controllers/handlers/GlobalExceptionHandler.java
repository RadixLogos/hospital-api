package com.radixlogos.hospitalapi.controllers.handlers;
import com.radixlogos.hospitalapi.dtos.errors.CustomError;
import com.radixlogos.hospitalapi.dtos.errors.ValidationError;
import com.radixlogos.hospitalapi.services.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        var err = new CustomError(Instant.now(),status.value(),e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status.value()).body(err);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<CustomError> userNameNotFound(UsernameNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        var err = new CustomError(Instant.now(),status.value(),e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status.value()).body(err);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<CustomError> invalidPassword(InvalidPasswordException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        var err = new CustomError(Instant.now(),status.value(),e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status.value()).body(err);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<CustomError> emailAlreadyRegistered(AlreadyExistsException e,HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        var err = new CustomError(Instant.now(),status.value(),e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status.value()).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError err = new ValidationError(Instant.now(),status.value(),"Dados inválidos",request.getRequestURI());
        for(FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addErrors(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status.value()).body(err);
    }

    @ExceptionHandler(NullValueException.class)
    public ResponseEntity<CustomError> nullValue(NullValueException e,HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        var err = new CustomError(Instant.now(),status.value(),e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status.value()).body(err);
    }

    @ExceptionHandler(ConstraintException.class)
    public ResponseEntity<CustomError> constraint(ConstraintException e,HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        var err = new CustomError(Instant.now(),status.value(),e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status.value()).body(err);
    }
}
