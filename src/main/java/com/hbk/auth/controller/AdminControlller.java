package com.hbk.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminControlller {

    @GetMapping
    public String welcome(){
        return "Welcome admin";
    }
}
