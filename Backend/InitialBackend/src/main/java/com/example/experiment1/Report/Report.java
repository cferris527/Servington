package com.example.experiment1.Report;

import com.example.experiment1.Post.Post;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String reportDescription;

    private String username;

    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "report_id", referencedColumnName = "title")
    private Post reportPost;

    public Report(String username, String title, String description){
        this.username = username;
        this.title = title;
        this.reportDescription = description;
    }

    public Report(){};

    public String getUsername(){return username; }

    public String getTitle(){return title; }

    public String getReportDescription(){return reportDescription; }


}
