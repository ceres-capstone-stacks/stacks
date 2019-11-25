package com.stack.stacks.controller;

import com.stack.stacks.models.Goal;
import com.stack.stacks.models.User;
import com.stack.stacks.repositories.ExpenseRepository;
import com.stack.stacks.repositories.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import java.util.Calendar;

@Controller
public class GoalController {

    private GoalRepository goalDao;


    public GoalController(GoalRepository goalDao) {
        this.goalDao = goalDao;
    }

    @GetMapping("/goals")
    public String index(Model vModel) {
        vModel.addAttribute("goals", goalDao.findAll());
        return "goals/index";
    }

//    @GetMapping("/goals")
//    public String showGoals(Model vModel) {
//        List<Goal> goalsList = goalDao.findAll();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//
//        for(Goal aGoal : goalsList ) {
//            String strDate= formatter.format(aGoal.getDate());
//            Date date1= null;
//            try {
//                date1 = new SimpleDateFormat("MM/dd/yyyy").parse(strDate);
//            } catch (ParseException e) {
//                System.out.println("invalid date format in goalsList");
//                break;
//            }
//            aGoal.setDate(date1);
//        }
//        vModel.addAttribute("goal", goalsList);
//        return "goals/index";
//    }

    @GetMapping("/goals/create")
    public String showGoalsIndex(Model vModel) {
        vModel.addAttribute("goal", new Goal());
        return "goals/createGoal";
    }
    @PostMapping("/goals/create")
    public String create(@ModelAttribute Goal goalToBeCreated){
        Goal savedGoal = goalDao.save(goalToBeCreated);
        return "redirect:/goals";
    }

    @PostMapping("/goals/{id}/delete")
    public String deleteGoal(@PathVariable long id) {
        goalDao.deleteById(id);
        return "redirect:/goals";
    }

    @GetMapping("/goals/{id}/edit")
    public String editPost(@PathVariable long id, Model vModel) {
        vModel.addAttribute("goals", goalDao.getOne(id));
        return "goals/edit";
    }

    @PostMapping("/goals/{id}/edit")
    public String update(@PathVariable long id, @RequestParam Double amountSaved, @RequestParam Date date) {
        Goal oldGoal = goalDao.getOne(id);
        oldGoal.setAmountSaved(amountSaved);
        oldGoal.setDate(date);
        goalDao.save(oldGoal);
        return "redirect:/goals/" + id;
    }

//    @PostMapping("/goals/create")
//    public String createGoal(@ModelAttribute Goal goal) {
//        goalDao.save(goal);
//        return "/goals/index";
//    }

    public GoalController() {
        super();
    }
}