package com.example.experiment1.Post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import com.example.experiment1.User.User;

@Entity
public class Post {


    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String title;

    private String date;

    private String message;


    @ManyToOne
    @JoinColumn(name = "username")
    @JsonIgnore
    private User user;

    public Post(){

    }

    public Post(String title, String date, String message){
        this.title = title;
        this.date = date;
        this.message = message;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
