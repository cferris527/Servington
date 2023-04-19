package com.example.experiment1.Volunteer;

import com.example.experiment1.Post.Post;

import com.example.experiment1.Team.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    private String email;

    private String phone_number;

    private String displayName;

    private String profilePictureUrl;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "volunteer")
    @JsonIgnore
    private List<Post> events;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "volunteers")
    @JsonIgnore
    private List<Team> teams;

    private boolean isBanned;

    public Volunteer(){
        events = new ArrayList<>();
    }

    public Volunteer(String username, String password, String displayName, String email, String phone_number, String profilePictureURL){
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.email = email;
        this.phone_number = phone_number;
        this.profilePictureUrl = profilePictureURL;
        events = new ArrayList<>();
        isBanned = false;
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

    public int removeEvent(Post p){
        int found = 0;
        Post delete = null;
        for (Post post : events){
            if(post.getTitle() == p.getTitle()){
                delete = post;
                found = 1;
            }
        }

        if (found == 1){
            events.remove(delete);
        }

        return found;  //returns 1 if you found a post to remove, 0 otherwise

    }



    public boolean getIsBanned(){ return isBanned; }

    public void setIsBanned(boolean isBanned) { this.isBanned = isBanned; }





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

    public void setProfilePictureUrl(String profilePictureUrl) {this.profilePictureUrl = profilePictureUrl; }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }
}
