package com.experiments.Demo1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping("/")
    public String printWelcome() {
        return "Welcome to my springboot experimentation";
    }
}
