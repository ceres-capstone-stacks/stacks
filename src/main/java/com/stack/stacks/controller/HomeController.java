package com.stack.stacks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model vModel){
        return "home";
    }

    @GetMapping("/about")
    public String showAboutUs () {
        return "about";
    }

}
