package com.example.experiment2;

public class PostBoard {

    String postMessage;

    String date;

    String user;

    public PostBoard(String postMessage, String date, String user){

        this.postMessage = postMessage;
        this.date = date;
        this.user = user;

    }

    public String getDate() {
        return date;
    }

    public String getPostMessage() {
        return postMessage;
    }

    public String getUser() {
        return user;
    }

    public void setPostMessage(String postMessage) {
        this.postMessage = postMessage;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
