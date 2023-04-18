package com.example.experiment1.Post;

import com.example.experiment1.Organization.Organization;
import com.example.experiment1.Organization.OrganizationRepository;
import com.example.experiment1.Report.Report;
import com.example.experiment1.Volunteer.Volunteer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany
    private List<Report> reportList;



    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    @JsonIgnore
    private Organization org;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "volunteer_to_post",
            joinColumns = { @JoinColumn(name = "post_title") },
            inverseJoinColumns = { @JoinColumn(name = "volunteer_id") })
    @JsonIgnore
    private List<Volunteer> volunteer;



    public Post(){
        volunteer = new ArrayList<>();
        reportList = new ArrayList<Report>();
    }

    public Post(String title, String date, String description){
        this.title = title;
        this.date = date;
        this.description = description;
        this.volunteerCount = 0;
        org = new Organization();
        volunteer = new ArrayList<>();
        reportList = new ArrayList<Report>();
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
        this.volunteerCount += 1;
    }

    public void decrementCount(){
        this.volunteerCount -= 1;
    }



    public void addVolunteer(Volunteer v) {
        this.volunteer.add(v);
    }

    public List<Volunteer> getVolunteers() {
        return volunteer;
    }

    public void removeVolunteer(Volunteer v){
        int found = 0;
        Volunteer delete = null;
        for (Volunteer vol : volunteer){
            if(vol.getId() == v.getId()){
                delete = vol;
                found = 1;
            }
        }

        if (found == 1){
            volunteer.remove(delete);
        }

    }
}
