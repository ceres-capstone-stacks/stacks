package com.stack.stacks.controller;

import com.stack.stacks.models.Goal;
import com.stack.stacks.models.User;
import com.stack.stacks.repositories.ExpenseRepository;
import com.stack.stacks.repositories.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
            if(goal.getUser() == currentUser){
                goals.add(goal);
            }
        }
        //Sout breaks it because nothing is being added to ArrayList
        System.out.println("goals.get(0).getDescription() = " + goals.get(0).getDescription());

        //Once fixed this should creat HashMap of formatted dates
        for(Goal thisGoal : goals){
            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
            Date thisDate = thisGoal.getDate();
            String dateString = formatter.format(thisDate);
            dates.put(thisGoal.getId(), dateString);
        }
        System.out.println("dates.get(1) = " + dates.get(1));
        vModel.addAttribute("dates", dates);
        vModel.addAttribute("goal", goals);
        return "/goals/index";
    }

    @GetMapping("/goals/create")
    public String showGoalsIndex(Model vModel) {
        vModel.addAttribute("goal", new Goal());
        return "/goals/createGoal";
    }
    @PostMapping("/goals/create")
    public String create(@ModelAttribute Goal goalToBeCreated){
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        goalToBeCreated.setUser(currentUser);
        Goal savedGoal = goalDao.save(goalToBeCreated);
        return "redirect:/goals";
    }

    @PostMapping("/goals/{id}/delete")
    public String deleteGoal(@PathVariable long id) {
        goalDao.deleteById(id);
        return "redirect:/goals";
    }

//    @GetMapping("/goals/{id}")
//    public String show(@PathVariable long id, Model vModel) {
//        vModel.addAttribute("goal", goalDao.getOne(id));
//        return "goals/index";
//    }

//    @PostMapping("/goals/create")
//    public String createGoal(@ModelAttribute Goal goal) {
//        goalDao.save(goal);
//        return "/goals/index";
//    }
}