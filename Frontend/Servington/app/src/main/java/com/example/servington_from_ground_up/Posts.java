package com.example.servington_from_ground_up;

public class Posts {


    private String postTitle;
    private String postDescription;
    private String postDate;

    public Posts(String postTitle, String postDescription, String postDate)
    {
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.postDate = postDate;
    }

    public String getPostTitle()
    {
        return postTitle;
    }

    public void setPostTitle(String postTitle)
    {
        this.postTitle = postTitle;
    }

    public String getPostDescription()
    {
        return postDescription;
    }

    public void setPostDescription(String postDescription)
    {
        this.postDescription = postDescription;
    }

    public String getPostDate()
    {
        return postDate;
    }

    public void setPostDate(String postDate)
    {
        this.postDate = postDate;
    }
}
