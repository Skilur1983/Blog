package com.mycompany.blog.model;

import jakarta.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private Post post;
    @ManyToOne
    @JoinColumn(name = "blogger_id", referencedColumnName = "id", nullable = false)
    private Blogger creator;

    public Comment(){}

    public Comment(String text, Post post, Blogger creator) {
        this.text = text;
        this.post = post;
        this.creator = creator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Blogger getCreator() {
        return creator;
    }

    public void setCreator(Blogger creator) {
        this.creator = creator;
    }
}
