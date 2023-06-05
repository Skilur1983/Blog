package com.mycompany.blog.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String body;
    @ManyToOne
    @JoinColumn(name = "blogger_id", referencedColumnName = "id", nullable = false)
    private Blogger blogger;
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

}
