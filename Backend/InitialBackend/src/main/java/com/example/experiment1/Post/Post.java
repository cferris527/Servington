package com.example.experiment1.Post;

import com.example.experiment1.Organization.Organization;
import com.example.experiment1.Organization.OrganizationRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Date;



@Entity
public class Post {


    @Id
    private String title;


    private String date;

    private String description;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Organization org;

    public Post(){

    }

    public Post(String title, String date, String description){
        this.title = title;
        this.date = date;
        this.description = description;
        org= new Organization();
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }
}
