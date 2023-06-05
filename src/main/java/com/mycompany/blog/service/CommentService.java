package com.mycompany.blog.service;

import com.mycompany.blog.model.Comment;
import com.mycompany.blog.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostService postService;

    public Optional<Comment> getCommentById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    public List<Comment> getAllCommentsByPostId(Long postId){
        return commentRepository.findAllById(postService.getPostIdByPostId(postId));
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }
}
