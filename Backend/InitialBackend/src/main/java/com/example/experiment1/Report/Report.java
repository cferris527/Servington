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


    private String username;

    private String title;

    public Report(String username, String title, String description){
        this.username = username;
        this.title = title;
        this.reportDescription = description;
    }

    public String getUser(){return username; }

    public String getPost(){return title; }

    public String getReportDescription(){return reportDescription; }

}
