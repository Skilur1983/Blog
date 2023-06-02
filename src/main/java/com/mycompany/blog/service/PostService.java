package com.mycompany.blog.service;


import com.mycompany.blog.model.Post;
import com.mycompany.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public Iterable<Long> getPostIdByPostId(Long id){
        return Collections.singleton(postRepository.findById(id).get().getId());
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }
}
