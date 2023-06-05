package com.mycompany.blog.controllers;

import com.mycompany.blog.model.Blogger;
import com.mycompany.blog.model.Comment;
import com.mycompany.blog.model.Post;
import com.mycompany.blog.service.CommentService;
import com.mycompany.blog.service.PostService;
import com.mycompany.blog.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private BloggerService bloggerService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/posts/{postId}")
    public String getPost(@PathVariable Long postId, Model model) {

        // find post by id
        Optional<Post> optionalPost = this.postService.getPostById(postId);

        // if post exists put it in model
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            List<Comment> comments = commentService.getAllCommentsByPostId(postId);
            model.addAttribute("comments", comments);
            return "post";
        } else {
            return "404";
        }
    }

    @PostMapping("/posts/{postId}")
    @PreAuthorize("isAuthenticated()")
    public String updatePost(@PathVariable Long postId, Post post, BindingResult result, Model model) {

        Optional<Post> optionalPost = postService.getPostById(postId);
        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();

            existingPost.setTitle(post.getTitle());
            existingPost.setBody(post.getBody());

            postService.save(existingPost);
        }

        return "redirect:/posts/" + postId;
    }

    @GetMapping("/posts/new")
    @PreAuthorize("isAuthenticated()")
    public String createNewPost(Model model, Principal principal) {

        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }

        Optional<Blogger> optionalBlogger = bloggerService.findOneByEmail(authUsername);
        if (optionalBlogger.isPresent()) {
            Post post = new Post();
            post.setBlogger(optionalBlogger.get());
            model.addAttribute("post", post);
            return "post_new";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/posts/new")
    @PreAuthorize("isAuthenticated()")
    public String createNewPost(@ModelAttribute Post post, Principal principal) {
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        if (post.getBlogger().getEmail().compareToIgnoreCase(authUsername) < 0) {
            // TODO: some kind of error?
            // our account email on the Post not equal to current logged in account!
        }
        postService.save(post);
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/posts/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String getPostForEdit(@PathVariable Long id, Model model) {

        // find post by id
        Optional<Post> optionalPost = postService.getPostById(id);
        // if post exist put it in model
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "post_edit";
        } else {
            return "404";
        }
    }

    @GetMapping("/posts/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deletePost(@PathVariable Long id) {

        // find post by id
        Optional<Post> optionalPost = postService.getPostById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            postService.delete(post);
            return "redirect:/";
        } else {
            return "404";
        }
    }
}
