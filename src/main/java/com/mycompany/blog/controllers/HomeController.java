package com.mycompany.blog.controllers;

import com.mycompany.blog.model.Post;
import com.mycompany.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String home(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "home";
    }
}
