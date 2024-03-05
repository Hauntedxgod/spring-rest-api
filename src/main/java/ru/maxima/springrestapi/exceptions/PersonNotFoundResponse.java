package ru.maxima.springrestapi.exceptions;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class PersonNotFoundResponse extends Response {


    public PersonNotFoundResponse(String message, Date date) {
        super(message, date);
    }
}
