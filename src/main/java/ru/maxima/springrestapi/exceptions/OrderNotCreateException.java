package ru.maxima.springrestapi.exceptions;

public class OrderNotCreateException extends RuntimeException{
    public OrderNotCreateException(String message) {
        super(message);
    }
}
