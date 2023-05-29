package com.mycompany.blog.controllers;

import com.mycompany.blog.model.Role;
import com.mycompany.blog.model.User;
import com.mycompany.blog.pojos.UserRegistrtion;
import com.mycompany.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenStore tokenStore;

    @PostMapping(value = "/register")
    public String register(@RequestBody UserRegistrtion userRegistrtion){
        if(!userRegistrtion.getPassword().equals(userRegistrtion.getPasswordConfirmation()))
            return "Passwords don't match";
        else if (userService.getUser(userRegistrtion.getUsername()) != null)
            return "User already exists";

        userService.save(new User(userRegistrtion.getUsername(), userRegistrtion.getPassword(), Arrays.asList(new Role("User"))));
        return "User created";
    }

    @GetMapping(value = "/users")
    public List<User> users(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/logouts")
    public void logout(@RequestParam (value = "access_token") String accessToken){
        tokenStore.removeAccessToken(tokenStore.readAccessToken(accessToken));
    }

    @GetMapping(value = "/getUsername")
    public String getUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
