package com.stack.stacks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {

    @GetMapping("/login")
    public String showLogin(){
        return "users/login";
    }

    @GetMapping("/register")
    public String showRegisterPage(){
        return "users/register";
    }

    @GetMapping("/profile")
    public String showProfile(){
        return "users/profile";
    }

}
