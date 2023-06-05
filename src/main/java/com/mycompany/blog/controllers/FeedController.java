package com.mycompany.blog.controllers;

import com.mycompany.blog.model.Comment;
import com.mycompany.blog.model.Post;
import com.mycompany.blog.service.CommentService;
import com.mycompany.blog.service.PostService;
import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Description;
import com.rometools.rome.feed.rss.Item;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class FeedController {

    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    @GetMapping(path = "/rss")
    public Channel rssFeed(HttpServletRequest request) {

        String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();

        Channel channel = new Channel();
        channel.setFeedType("rss_2.0");
        channel.setTitle("Spring Boot Blog Application");
        channel.setDescription("My Spring Boot Blog Demo App");
        channel.setLink(baseUrl);
        channel.setUri(baseUrl);
        channel.setGenerator("Custom Sauce");

        Date postDate = new Date();
        channel.setPubDate(postDate);
        List<Item> feed = new ArrayList<>();
        List<Post> posts = postService.getAllPosts();
        for(Post post: posts) {
            Item item = new Item();
            item.setAuthor("Foo");
            item.setLink(baseUrl + "/posts/" + post.getId());
            item.setTitle(post.getTitle());
            item.setUri(baseUrl + "/posts/" + post.getId());

            Description descr = new Description();
            descr.setValue(post.getBody());
            item.setDescription(descr);

            feed.add(item);
        }

        channel.setItems(feed);

        return channel;
    }
}
