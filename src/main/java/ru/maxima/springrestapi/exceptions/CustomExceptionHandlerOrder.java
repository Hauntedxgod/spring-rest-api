package ru.maxima.springrestapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandlerOrder extends ResponseEntityExceptionHandler {

    @ExceptionHandler({OrderNotCreateException.class})
    public ResponseEntity<Object> handleException(OrderNotCreateException ex) {
        return new ResponseEntity<>(ex.getMessage() , HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler({OrderNotFoundException.class})
    public ResponseEntity<Object> handleException(OrderNotFoundException ex) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
