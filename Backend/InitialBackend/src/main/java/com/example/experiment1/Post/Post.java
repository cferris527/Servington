package com.example.experiment1.Post;

import com.example.experiment1.Organization.Organization;
import com.example.experiment1.Organization.OrganizationRepository;
import com.example.experiment1.Volunteer.Volunteer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Post {


    @Id
    private String title;


    private String date;

    private String description;

    private int volunteerCount;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Organization org;

    @ManyToMany
    @Column(nullable = true)
    @JsonIgnore
    private List<Volunteer> volunteers;
    

    public Post(){

    }

    public Post(String title, String date, String description){
        this.title = title;
        this.date = date;
        this.description = description;
        this.volunteerCount = 0;
        org= new Organization();
        volunteers = new ArrayList<>();
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

    public int getVolunteerCount(){
        return volunteerCount;
    }

    public void incrementCount(){
        this.volunteerCount ++;
    }



    public void addVolunteer(Volunteer v) {
        this.volunteers.add(v);
    }

    public List<Volunteer> getVolunteers() {
        return volunteers;
    }
}
