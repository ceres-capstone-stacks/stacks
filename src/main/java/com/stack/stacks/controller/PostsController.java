package com.stack.stacks.controller;

import com.stack.stacks.models.Post;
import com.stack.stacks.models.Tag;
import com.stack.stacks.models.User;
import com.stack.stacks.repositories.GoalRepository;
import com.stack.stacks.repositories.PostRepository;
import com.stack.stacks.repositories.TagRepository;
import com.stack.stacks.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        model.addAttribute("tags", tagDao.findAll());
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String create(@ModelAttribute Post postToBeCreated, @RequestParam(value = "tagged" , required = false) int[] tagged , BindingResult bindingResult , Model model) {
        List<Tag> postTags = new ArrayList<>();
        if (tagged != null) {
            Tag tag = null;
            for (int i = 0; i < tagged.length; i++) {

                if (tagDao.existsById((long) tagged[i])) {
                    tag = tagDao.getOne((long) tagged[i]);
                    postTags.add(tag);
                    System.out.println("postTags.get(i).getName() = " + postTags.get(i).getName());
                }
            }

        }
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postToBeCreated.setTags(postTags);
        postToBeCreated.setUser(currentUser);
        for (int i = 0; i < postToBeCreated.getTags().size(); i++) {
            System.out.println(postToBeCreated.getTags().get(i).getName());
        }
        postDao.save(postToBeCreated);
        return "redirect:/posts";
    }

    @GetMapping("/posts/myposts")
    public String usersPosts(Model vModel){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Post> allPosts = postDao.findAll();
        List<Post> posts = new ArrayList<>();
        for(Post post : allPosts){
            if(post.getUser() !=null) {
                if (post.getUser().getId() == currentUser.getId()) {
                    posts.add(post);
                }
            }
        }
        vModel.addAttribute("post",allPosts);
        return "posts/userCreated";
    }

    @GetMapping("/posts/favorites")
    public String showFavorites(){
        return "posts/favorites";
    }

    // to get the right post you want to edit
    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, Model vModel) {
        vModel.addAttribute("posts", postDao.getOne(id));
        return "posts/editPost";
    }
//    @GetMapping("/posts/{id}/edit")
//    public String editPost(@PathVariable long id, Model vModel) {
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User postUser = postDao.getOne(id).getUser();
//        if(!currentUser.equals(postUser)){
//            return "redirect:/posts";
//        } else {
//            vModel.addAttribute("posts", postDao.getOne(id));
//            return "posts/editPost";
//        }
//    }

    // to post the changes made to post
    @PostMapping("/posts/{id}/edit")
    public String update(@PathVariable long id, @RequestParam String title, @RequestParam String content) {
        Post oldPost = postDao.getOne(id);
        oldPost.setTitle(title);
        oldPost.setContent(content);
        postDao.save(oldPost);
        return "redirect:/posts";
    }

    // to delete
    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        postDao.deleteById(id);
        return "redirect:/posts/myposts";
    }
}
