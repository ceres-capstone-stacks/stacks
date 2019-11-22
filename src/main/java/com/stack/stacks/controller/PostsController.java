package com.stack.stacks.controller;

import com.stack.stacks.models.Post;
import com.stack.stacks.models.User;
import com.stack.stacks.repositories.PostRepository;
import com.stack.stacks.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostsController {

    private final PostRepository postDao;
    private final UserRepository userDao;


    public PostsController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String showPosts(Model x){
        x.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }



    @PostMapping("/posts/create")
    public String create(@ModelAttribute Post postToBeCreated) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postToBeCreated.setUser(currentUser);
        postDao.save(postToBeCreated);
        return "redirect:/posts";
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
