package com.stack.stacks.controller;

import com.stack.stacks.models.Expense;
import com.stack.stacks.models.Goal;
import com.stack.stacks.models.Post;
import com.stack.stacks.models.User;
import com.stack.stacks.repositories.ExpenseRepository;
import com.stack.stacks.repositories.GoalRepository;
import com.stack.stacks.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.Errors;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {
    private UserRepository userDao;
    private ExpenseRepository expenseDao;
    private PasswordEncoder passwordEncoder;
    private GoalRepository goalDao;

    public UserController(UserRepository userDao,ExpenseRepository expenseDao,PasswordEncoder passwordEncoder, GoalRepository goalDao){
        this.userDao = userDao;
        this.expenseDao = expenseDao;
        this.passwordEncoder = passwordEncoder;
        this.goalDao = goalDao;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model vmModel){
        vmModel.addAttribute("user", new User());
        return "users/register";
    }
    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String showProfile(Model vModel){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Expense> onlyFive = expenseDao.firstFiveExpenses();
        Double sumOfExpenses = expenseDao.sumOfExpenses(loggedInUser.getId());
        List<Goal> allGoals = goalDao.findAll();
        List<Goal> goals = new ArrayList<>();
        HashMap<Long, String> dates = new HashMap<>();
        //Loop to find goals specific to user
        for(Goal goal : allGoals){
            if(goal.getUser() != null) {
                if (goal.getUser().getId() == loggedInUser.getId()) {
                    goals.add(goal);
                }
            }
        }
        vModel.addAttribute("goal", goals);
        vModel.addAttribute("expense", new Expense());
        vModel.addAttribute("expenses", onlyFive);
        vModel.addAttribute("user", loggedInUser);
        vModel.addAttribute("sum",sumOfExpenses);
        return "users/profile";
    }




    @GetMapping("/profile/expenses")
    public String getExpenses(Model vModel) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Expense> allExpenses = expenseDao.findAll();
//        List<Expense> sumOfExpenses = expenseDao.sumOfExpenses();
        List<Expense> expenses = new ArrayList<>();
        for(Expense expense : allExpenses){
            if(expense.getUser() != null) {
                if (expense.getUser().getId() == currentUser.getId()) {
                    expenses.add(expense);
                }
            }
        }
        vModel.addAttribute("expense", new Expense());
        vModel.addAttribute("expenses", expenses);
        return "expenses/index";
    }

    @GetMapping("/profile/expenses/create")
    public String showCreateForm(Model model) {
        model.addAttribute("expense", new Expense());
        return "expenses/create";
    }

    @PostMapping("/profile/expenses/create")
    public String create(@ModelAttribute Expense expenseToBeCreated, @RequestParam(defaultValue = "false") boolean isRegular) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        expenseToBeCreated.setUser(currentUser);
        expenseToBeCreated.setRegular(isRegular);
        expenseDao.save(expenseToBeCreated);
        return "redirect:/profile/expenses";
    }

    @GetMapping("/profile/expenses/{id}/edit")
    public String editExpense(@PathVariable long id, Model vModel){
        vModel.addAttribute("expenses", expenseDao.getOne(id));
        return "expenses/editExpense";
    }

    @PostMapping("/profile/expenses/{id}/edit")
    public String updateExpense(@PathVariable long id, @RequestParam String Date, @RequestParam String description, @RequestParam int type, @RequestParam double amount) {
        Expense oldExpense = expenseDao.getOne(id);
        oldExpense.setDate(Date);
        oldExpense.setDescription(description);
        oldExpense.setType(type);
        oldExpense.setAmount(amount);
        expenseDao.save(oldExpense);
        return "redirect:/profile/expenses";
    }

    // to delete
    @PostMapping("/profile/expenses/{id}/delete")
    public String deleteExpense(@PathVariable long id) {
        expenseDao.deleteById(id);
        return "redirect:/profile/expenses";
    }
}
