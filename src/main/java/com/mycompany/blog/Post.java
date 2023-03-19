/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blog;

/**
 *
 * @author dmitry
 */
public class Post {
    private int id;
    private static int nextId = 0;
    private String postTest;

    public Post(String postTest) {
        this.id = nextId++;
        this.postTest = postTest;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostTest() {
        return postTest;
    }

    public void setPostTest(String postTest) {
        this.postTest = postTest;
    }
    
    
}
