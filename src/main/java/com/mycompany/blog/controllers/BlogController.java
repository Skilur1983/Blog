package com.mycompany.blog.controllers;

import com.mycompany.blog.config.CustomUserDetails;
import com.mycompany.blog.model.Post;
import com.mycompany.blog.service.PostService;
import com.mycompany.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class BlogController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

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
}
