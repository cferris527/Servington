package com.example.experiment1.Organization;

import com.example.experiment1.Post.Post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    //private String email;

    //private char[] phoneNumber = new char[10];

    //private String displayName;

    //store as URL
    //private String profilePictureURL;


    @OneToMany(mappedBy = "title")
    @JsonIgnore
    private List<Post> posts;

    public Organization(){
        posts = new ArrayList<>();
    }

    public Organization(String username, String password){
        this.username = username;
        this.password = password;
        posts = new ArrayList<>();
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getUsername(){
        return username;
    }


    public void setUsername(String name) {
        this.username = name;
    }




    public List<Post> getPost() {
        return posts;
    }

    public void setPost(Post post){
        posts.add(post);
    }


}


