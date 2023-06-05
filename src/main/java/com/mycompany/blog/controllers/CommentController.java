package com.mycompany.blog.controllers;

import com.mycompany.blog.model.Blogger;
import com.mycompany.blog.model.Comment;
import com.mycompany.blog.model.Post;
import com.mycompany.blog.service.BloggerService;
import com.mycompany.blog.service.CommentService;
import com.mycompany.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private PostService postService;

    @GetMapping("/comments/{id}")
    public String getComment(@PathVariable Long commentId, Model model) {

        Optional<Comment> optionalComment = this.commentService.getCommentById(commentId);

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            model.addAttribute("comment", comment);
            return "comment";
        } else {
            return "404";
        }
    }

    @GetMapping("/posts/{postId}/comments/new")
    @PreAuthorize("isAuthenticated()")
    public String createNewComment(@PathVariable Long postId, Model model, Principal principal) {

        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }

        Optional<Blogger> optionalBlogger = bloggerService.findOneByEmail(authUsername);
        Optional<Post> optionalPost = postService.getPostById(postId);
        if (optionalBlogger.isPresent()) {
            Comment comment = new Comment();
            comment.setBlogger(optionalBlogger.get());
            comment.setPost(optionalPost.get());
            model.addAttribute("comment", comment);
            return "comment_new";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/posts/{postId}/comments/new")
    @PreAuthorize("isAuthenticated()")
    public String createNewComment(@ModelAttribute Comment comment, @PathVariable Long postId, Principal principal) {
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        if (comment.getBlogger().getEmail().compareToIgnoreCase(authUsername) < 0) {
            // TODO: some kind of error?
            // our Blogger email on the Post not equal to current logged in Blogger!
        }
        commentService.save(comment);
        return "redirect:/posts/" + postId;
    }

    @GetMapping("/comments/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteComment(@PathVariable Long id) {

        // find post by id
        Optional<Comment> optionalComment = commentService.getCommentById(id);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();

            commentService.delete(comment);
            return "redirect:/";
        } else {
            return "404";
        }
    }

}
