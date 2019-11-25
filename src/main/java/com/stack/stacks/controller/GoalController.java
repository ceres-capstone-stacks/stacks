package com.stack.stacks.controller;

import com.stack.stacks.models.Goal;
import com.stack.stacks.models.User;
import com.stack.stacks.repositories.GoalRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GoalController {

    private GoalRepository goalDao;


    public GoalController(GoalRepository goalDao) {
        this.goalDao = goalDao;
    }


    @GetMapping("/goals")
    public String showGoalsIndex () {
        return "goals/index";
    }

    @GetMapping("/goals/create")
    public String showGoalsIndex(Model vModel) {
        vModel.addAttribute("goal", new Goal());
        return "goals/create";
    }
    @PostMapping("/goals/create")
    public String create(@ModelAttribute Goal goalToBeCreated){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        goalToBeCreated.setUser(currentUser);
        Goal savedGoal = goalDao.save(goalToBeCreated);
        return "redirect:/goals";
    }
}
