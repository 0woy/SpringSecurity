package com.example.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(){
        return "main";
    }

    @GetMapping("/admin")
    public String adminP(){
        return "admin";
    }

    @GetMapping("/login")
    public String loginP(){
        return "login";
    }
}
