package com.example.experiment1.Report;

import com.example.experiment1.Post.Post;
import com.example.experiment1.User.User;
import jakarta.persistence.*;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String reportDescription;

    private User userReported;

    private Post postReported;

    public Report(User user, Post post, String description){
        this.userReported = user;
        this.postReported = post;
        this.reportDescription = description;
    }

    public User getUser(){return userReported; }

    public Post getPost(){return postReported; }

    public String getReportDescription(){return reportDescription; }

}
