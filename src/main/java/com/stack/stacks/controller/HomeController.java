package com.stack.stacks.controller;

import com.stack.stacks.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    private final UserRepository userDao;

    public HomeController (UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/")
    public String index(Model vModel){
        vModel.addAttribute(userDao.findAll());
        return "home";
    }

    @GetMapping("/about")
    public String showAboutUs () {
        return "about";
    }

    @RequestMapping("/")
    public void handleRequest() {
        throw new RuntimeException("test exception");
    }
}
