package com.mycompany.blog.controllers;

import com.mycompany.blog.model.Blogger;
import com.mycompany.blog.model.Comment;
import com.mycompany.blog.model.Post;
import com.mycompany.blog.service.BloggerService;
import com.mycompany.blog.service.CommentService;
import com.mycompany.blog.service.PostService;
import jakarta.websocket.server.PathParam;
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

    @GetMapping("/posts/{postId}/comments/new")
    public String navigateToCommentPage(@PathVariable("postId")Long postId, Model model, Principal principal){

        Optional<Post> optionalPost = postService.getPostById(postId);
        if(optionalPost.isPresent())
            model.addAttribute("post", optionalPost.get());
        else
            return "404";
        return "comment_post_new";
    }


    @PostMapping("/posts/{postId}/comments/new")
    @PreAuthorize("isAuthenticated()")
    public String createNewComment(@PathVariable("postId")Long postId, @ModelAttribute ("comment") Comment newComment, Principal principal) {

        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }

        Comment comment = new Comment();
        comment.setCreator(bloggerService.findOneByEmail(principal.getName()).get());
        comment.setPost(postService.getPostById(postId).get());
        comment.setText(newComment.getText());
        commentService.save(comment);
            return "redirect:/posts/" + postId;
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
