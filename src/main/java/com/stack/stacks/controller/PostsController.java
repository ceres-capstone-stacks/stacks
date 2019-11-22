package com.stack.stacks.controller;

import com.stack.stacks.models.Post;
import com.stack.stacks.models.Tag;
import com.stack.stacks.models.User;
import com.stack.stacks.repositories.PostRepository;
import com.stack.stacks.repositories.TagRepository;
import com.stack.stacks.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostsController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final TagRepository tagDao;


    public PostsController(PostRepository postDao, UserRepository userDao, TagRepository tagDao) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.tagDao = tagDao;
    }

    @GetMapping("/posts")
    public String showPosts(Model x){
        x.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/create")
    public String showCreateForm(Model model) {
        List<Tag> tags = tagDao.findAll();
        model.addAttribute("tags", tags);
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

//    @RequestMapping(value = "/tags/create", method = RequestMethod.POST)
//    @ResponseStatus(value = HttpStatus.OK)
//    public void addTagsToPosts(@RequestParam("tagged") List<String> tagged) {
//        List<Tag> allTags = tagDao.findAll();
//        List<Tag> postTags = new ArrayList<>();
//        for(String tag : tagged){
//            long tagId = Long.parseLong(tag);
//            for(Tag tag1 : allTags){
//                if(tagId == tag1.getId()){
//                    postTags.add(tag1);
//                }
//            }
//        }
//        postToBeCreated.setTags(postTags);
//    }


    @GetMapping("/posts/myposts")
    public String usersPosts(){
        return "posts/user_created";
    }

    @GetMapping("/posts/favorites")
    public String showFavorites(){
        return "posts/favorites";
    }

}
