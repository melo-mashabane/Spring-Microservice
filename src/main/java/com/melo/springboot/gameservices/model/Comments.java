package com.melo.springboot.gameservices.model;


public class Comments {

    private String user;
    private String message;
    private String dateCreated;
    private String like;


    public Comments(String user, String message, String dateCreated, String like) {
        this.user = user;
        this.message = message;
        this.dateCreated = dateCreated;
        this.like = like;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }
}