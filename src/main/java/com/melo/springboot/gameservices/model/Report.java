package com.melo.springboot.gameservices.model;

public class Report {

    private String title;
    private String average_likes;

    public Report(String title, String average_likes) {
        this.title = title;
        this.average_likes = average_likes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAverage_likes() {
        return average_likes;
    }

    public void setAverage_likes(String average_likes) {
        this.average_likes = average_likes;
    }
}
