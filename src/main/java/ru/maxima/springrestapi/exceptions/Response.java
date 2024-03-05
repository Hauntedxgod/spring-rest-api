package ru.maxima.springrestapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


public class Response {
    private String message;

    public Date date;

    public Response(String message, Date date) {
        this.message = message;
        this.date = date;
    }
}
