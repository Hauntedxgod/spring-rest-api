package ru.maxima.springrestapi.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MyFirstController {

    @ResponseBody
    @GetMapping("/hello-endpoint")
    public String sayHello(){
        return "Hello World!";
    }


}
