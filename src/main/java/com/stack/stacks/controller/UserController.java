package com.stack.stacks.controller;

import com.stack.stacks.models.User;
import com.stack.stacks.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao,PasswordEncoder passwordEncoder){
        this.userDao = userDao;
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
        System.out.println("This is registering something");
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

        return "/profile";
    }
}
