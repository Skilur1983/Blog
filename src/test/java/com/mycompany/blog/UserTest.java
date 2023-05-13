package com.mycompany.blog;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testGetEmail() {
        User user = new User("test@example.com", "John Doe", "Developer");
        Assertions.assertEquals("test@example.com", user.getEmail());
    }

    @Test
    public void testSetEmail() {
        User user = new User();
        user.setEmail("test@example.com");
        Assertions.assertEquals("test@example.com", user.getEmail());
    }

    @Test
    public void testGetName() {
        User user = new User("test@example.com", "John Doe", "Developer");
        Assertions.assertEquals("John Doe", user.getName());
    }

    @Test
    public void testSetName() {
        User user = new User();
        user.setName("John Doe");
        Assertions.assertEquals("John Doe", user.getName());
    }

    @Test
    public void testGetPost() {
        User user = new User("test@example.com", "John Doe", "Developer");
        Assertions.assertEquals("Developer", user.getPost());
    }

    @Test
    public void testSetPost() {
        User user = new User();
        user.setPost("Developer");
        Assertions.assertEquals("Developer", user.getPost());
    }
}