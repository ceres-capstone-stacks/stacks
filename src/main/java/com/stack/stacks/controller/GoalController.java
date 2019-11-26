package com.stack.stacks.controller;

import com.stack.stacks.models.Goal;
import com.stack.stacks.models.User;
import com.stack.stacks.repositories.GoalRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class GoalController {

    private GoalRepository goalDao;


    public GoalController(GoalRepository goalDao) {
        this.goalDao = goalDao;
    }

    @GetMapping("/goals")
    public String showGoals(Model vModel) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Goal> allGoals = goalDao.findAll();
        List<Goal> goals = new ArrayList<>();
        HashMap<Long, String> dates = new HashMap<>();
        //Loop to find goals specific to user
        for(Goal goal : allGoals){
            if(goal.getUser() != null) {
                if (goal.getUser().getId() == currentUser.getId()) {
                    goals.add(goal);
                }
            }
        }
        vModel.addAttribute("goal", goals);
        return "goals/index";
    }

    @GetMapping("/goals/create")
    public String showGoalsIndex(Model vModel) {
        vModel.addAttribute("goal", new Goal());
        return "/goals/create";
    }
    @PostMapping("/goals/create")
    public String create(@ModelAttribute Goal goalToBeCreated){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        goalToBeCreated.setUser(currentUser);
        Goal savedGoal = goalDao.save(goalToBeCreated);
        return "redirect:/goals";
    }

    @PostMapping("/goals/{id}/delete")
    public String deleteGoal(@PathVariable long id) {
        goalDao.deleteById(id);
        return "redirect:/goals";
    }

    @GetMapping("/goals/{id}/edit")
    public String editGoal(@PathVariable long id, Model vModel) {
        vModel.addAttribute("goals",goalDao.getOne(id));
        return "goals/editGoal";
    }

    @PostMapping("/goals/{id}/edit")
    public String updateGoal (@PathVariable long id, @RequestParam double amountSaved, @RequestParam String date) {
        Goal oldGoal = goalDao.getOne(id);
        oldGoal.setAmountSaved(amountSaved);
        oldGoal.setDate(date);
        goalDao.save(oldGoal);
        return "redirect:/goals";
    }
}