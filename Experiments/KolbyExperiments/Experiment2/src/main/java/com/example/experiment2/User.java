package com.example.experiment2;

public class User {

    private String username;
    private String password;
    private String accountType;


    public User(String username, String password, String accountType, String displayName){

        this.username = username;
        this.password = password;
        this.accountType = accountType;

    }

    public String getUsername(){
        return username;

    }

    public String getPassword() {
        return password;
    }

    public String getAccountType(){
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
