package com.example.experiment1.User;

import com.example.experiment1.Post.Post;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String accountType;

    private String username;

    private String password;




    @OneToMany
    private List<Post> posts;

    public User(){

    }

    public User(String username, String password, String accountType){
        this.username = username;
        this.password = password;
        this.accountType = accountType;
        posts = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername(){
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }


    public List<Post> getPosts() {
        return posts;
    }

    public void addPosts(Post post){
        this.posts.add(post);
    }
}
