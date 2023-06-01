package com.mycompany.blog.controllers;

import com.mycompany.blog.model.Blogger;
import com.mycompany.blog.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private BloggerService bloggerService;

    @GetMapping("/register")
    public String getRegisterForm(Model model) {

        Blogger account = new Blogger();
        model.addAttribute("account", account);
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute Blogger account) {
        bloggerService.save(account);
        return "redirect:/";
    }
}
