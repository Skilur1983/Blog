package com.mycompany.blog.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String body;
    @ManyToOne
    @JoinColumn(name = "blogger_id", referencedColumnName = "id", nullable = false)
    private Blogger creator;
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Blogger getCreator() {
        return creator;
    }

    public void setCreator(Blogger creator) {
        this.creator = creator;
    }
}
