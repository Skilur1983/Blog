package com.mycompany.blog.controllers;

import com.mycompany.blog.config.CustomUserDetails;
import com.mycompany.blog.model.Post;
import com.mycompany.blog.service.PostService;
import com.mycompany.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String index(){
        return "index";
    }
    @GetMapping(value = "/posts")
    public List<Post> posts(){
        return postService.getAllPosts();
    }

    @PostMapping(value = "/post")
    public void publishPost(@RequestBody Post post){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setCreator(userService.getUser(userDetails.getUsername()));
        postService.insert(post);
    }

    @GetMapping(value = "/posts/{username}")
    public List<Post> postsByUser(@PathVariable String username){
        return postService.findByUser(userService.getUser(username));
    }
}
