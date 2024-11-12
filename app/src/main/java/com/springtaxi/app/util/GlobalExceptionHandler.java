package com.springtaxi.app.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springtaxi.app.exception.ElementAlreadyExistsException;
import com.springtaxi.app.exception.ElementNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ValidationObj handleBindException(BindException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = error.getDefaultMessage();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ValidationObj(ex.getObjectName() + " input errors (" + ex.getErrorCount() + ")", errors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseObj handElementNotFoundException(ElementNotFoundException ex) {
        return new ResponseObj(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ElementAlreadyExistsException.class)
    public ResponseObj handElementAlreadyExistsException(ElementAlreadyExistsException ex) {
        return new ResponseObj(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
    
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseObj handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return new ResponseObj(HttpStatus.BAD_GATEWAY.value(), ex.getMessage());
    }
    
}
