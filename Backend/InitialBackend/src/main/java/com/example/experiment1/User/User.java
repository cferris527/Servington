package com.example.experiment1.User;

import com.example.experiment1.Post.Post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String accountType;

    private String username;

    private String password;

    private String email;

    private char[] phoneNumber = new char[10];

    private String displayName;

    //store as URL
    private String profilePictureURL;


    @OneToMany(mappedBy = "id")
    private List<Post> post;

    public User(){

    }

    public User(String username, String password, String accountType){
        this.username = username;
        this.password = password;
        this.accountType = accountType;
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

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(Post apost){
        post.add(apost);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName){
        this.displayName = displayName;
    }

    public char[] getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(char[] phoneNumber){this.phoneNumber = phoneNumber; }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public void setProfilePictureURL(String URL){
        this.profilePictureURL = profilePictureURL;
    }
}
