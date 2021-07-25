package edu.sabanciuniv.ipamdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/")
    public String getString() {
        return "some handsome string";
    }
}
