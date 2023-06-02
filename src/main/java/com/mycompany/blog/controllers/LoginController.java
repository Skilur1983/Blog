package com.mycompany.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String getLogin() {

        return "login";
    }

    @GetMapping("/account")
    public String getAccount() {

        return "account";
    }
}
