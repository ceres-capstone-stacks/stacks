package com.stack.stacks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostsController {

    @GetMapping("/posts")
    public String showPosts(){
        return "posts/index";
    }

    @GetMapping("/posts/create")
    public String createPosts(){
        return "posts/create";
    }

    @GetMapping("/posts/myposts")
    public String usersPosts(){
        return "posts/user_created";
    }

    @GetMapping("/posts/favorites")
    public String showFavorites(){
        return "posts/favorites";
    }

}