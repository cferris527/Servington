package com.example.experiment1.Organization;

import com.example.experiment1.Post.Post;

import com.example.experiment1.Team.Team;
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

    private String email;

    private String phone_number;

    private String displayName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    @JsonIgnore
    private Team orgTeam;

    @OneToMany(mappedBy = "title")
    @JsonIgnore
    private List<Post> posts;

    public Organization(){
        posts = new ArrayList<>();
    }

    public Organization(String username, String password, String displayName, String email, String phone_number, String profilePictureURL){
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.email = email;
        this.phone_number = phone_number;
        posts = new ArrayList<>();
        orgTeam = new Team();
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



    public void setOrgTeam(Team t) {
        orgTeam = t;
    }

    public Team getOrgTeam(){ return orgTeam; }







    //editing other fields


    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPhone_number() {
        return phone_number;
    }


}


