package com.stack.stacks.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String loginForm () {
        Authentication token = SecurityContextHolder.getContext().getAuthentication();

        // This means the user is not logged in
        if (token instanceof AnonymousAuthenticationToken) return "users/login";

        // Redirect to the configured home page
        return String.format("redirect:%s", "/profile");
    }

    @PostMapping("/login")
    public String showProfile () {
        return "redirect:/profile";
    }
}