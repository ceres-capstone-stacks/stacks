package com.stack.stacks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoalController {


    @GetMapping("/goals")
    public String showGoalsIndex () {
        return "/goals/index";
    }

    @GetMapping ("/goals/create")
    public String createGoal () {
        return "/goals/create";
    }
}
