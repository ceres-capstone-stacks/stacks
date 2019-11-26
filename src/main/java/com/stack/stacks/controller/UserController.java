package com.stack.stacks.controller;

import com.stack.stacks.models.Expense;
import com.stack.stacks.models.Post;
import com.stack.stacks.models.User;
import com.stack.stacks.repositories.ExpenseRepository;
import com.stack.stacks.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private UserRepository userDao;
    private ExpenseRepository expenseDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao,ExpenseRepository expenseDao,PasswordEncoder passwordEncoder){
        this.userDao = userDao;
        this.expenseDao = expenseDao;
        this.passwordEncoder = passwordEncoder;
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
        vModel.addAttribute("user", loggedInUser);
        return "users/profile";
    }

    @GetMapping("/profile/expenses")
    public String getExpenses(Model vModel) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Expense> allExpenses = expenseDao.findAll();
        List<Expense> expenses = new ArrayList<>();
        try {
            for(Expense expense : allExpenses){
                if(expense.getUser().getId() == currentUser.getId()){
                    expenses.add(expense);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            return "redirect:/profile";
        }
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
}
