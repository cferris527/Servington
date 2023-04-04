package com.example.experiment1.Volunteer;

import com.example.experiment1.Post.Post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Volunteer{

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

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "volunteers")
    private List<Post> events;



    public Volunteer(){
        events = new ArrayList<>();
    }

    public Volunteer(String username, String password){
        this.username = username;
        this.password = password;
        events = new ArrayList<>();
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

    public void addEvent(Post post) {
        this.events.add(post);
    }

    public List<Post> getEvents() {
        return events;
    }
}
