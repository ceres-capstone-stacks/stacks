package com.stack.stacks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoalController {


    @GetMapping("/")

    @GetMapping ("/goals")
    public String createGoal () {
        return "/goals/create";
    }
}
