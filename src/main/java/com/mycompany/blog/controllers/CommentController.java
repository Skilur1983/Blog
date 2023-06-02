package com.mycompany.blog.controllers;

import com.mycompany.blog.model.Blogger;
import com.mycompany.blog.model.Comment;
import com.mycompany.blog.service.BloggerService;
import com.mycompany.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BloggerService bloggerService;

    @GetMapping("/posts/{postId}/comments/{id}")
    public String getComment(@PathVariable Long id, Model model) {

        Optional<Comment> optionalComment = this.commentService.getCommentById(id);

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            model.addAttribute("comment", comment);
            return "/posts/{postId}";
        } else {
            return "404";
        }
    }

    @PostMapping("/posts/{postId}/comments/{id}")
    @PreAuthorize("isAuthenticated()")
    public String updatePost(@PathVariable Long id, Comment comment, BindingResult result, Model model) {

        Optional<Comment> optionalComment = commentService.getCommentById(id);
        if (optionalComment.isPresent()) {
            Comment existingComment = optionalComment.get();

            existingComment.setText(comment.getText());

            commentService.save(existingComment);
        }

        return "redirect:/posts/{postId}" + comment.getId();
    }

    @GetMapping("/posts/{postId}/comments/new")
    @PreAuthorize("isAuthenticated()")
    public String createNewPost(Model model, Principal principal) {

        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }

        Optional<Blogger> optionalAccount = bloggerService.findOneByEmail(authUsername);
        if (optionalAccount.isPresent()) {
            Comment comment = new Comment();
            comment.setCreator(optionalAccount.get());
            model.addAttribute("comment", comment);
            return "/posts/{postId}";
        } else {
            return "redirect:/posts/{postId}";
        }
    }

    @PostMapping("/posts/{postId}/comments/new")
    @PreAuthorize("isAuthenticated()")
    public String createNewPost(@ModelAttribute Comment comment, Principal principal) {
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        if (comment.getCreator().getEmail().compareToIgnoreCase(authUsername) < 0) {
            return "Email on the Comment is not equal to current logged in account!";
        }
        commentService.save(comment);
        return "redirect:/posts/{postID}" + comment.getId();
    }

    @GetMapping("/posts/{postId}/comments/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteComments(@PathVariable Long id) {

        Optional<Comment> optionalComment = commentService.getCommentById(id);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();

            commentService.delete(comment);
            return "redirect:/posts/{postId}";
        } else {
            return "404";
        }
    }
}
